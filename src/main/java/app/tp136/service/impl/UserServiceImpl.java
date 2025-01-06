package app.tp136.service.impl;

import app.tp136.dto.UserDto;
import app.tp136.dto.request.UserResetPasswordRequestDto;
import app.tp136.dto.request.UserUpdatePasswordRequestDto;
import app.tp136.dto.request.UserUpdateProfileRequestDto;
import app.tp136.dto.responce.UserUpdateResponseDto;
import app.tp136.exception.DuplicateRoleException;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.exception.PasswordValidationException;
import app.tp136.mapper.UserMapper;
import app.tp136.model.Role;
import app.tp136.model.User;
import app.tp136.model.UserVerification;
import app.tp136.repository.RoleRepository;
import app.tp136.repository.UserRepository;
import app.tp136.repository.UserVerificationRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserVerificationRepository userVerificationRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateRole(Authentication authentication, String roleName) {
        User user = userDetailsService.getUserFromAuthentication(authentication);
        Role roleForUpdate = convertsToRole(roleName);
        Set<Role> roles = user.getRoles();
        ensureNoDuplicateRole(roles, roleForUpdate);
        roles.add(roleForUpdate);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserDto getProfile(Authentication authentication) {
        User user = userDetailsService.getUserFromAuthentication(authentication);
        UserDto dto = userMapper.toUserDto(user);
        UserVerification userVerification = userVerificationRepository
                .findByEmailAndType(user.getEmail(), UserVerification.Type.VERIFICATION)
                .orElseThrow(() -> new EntityNotFoundException("Verification not found"));
        dto.setVerified(userVerification.isVerified());
        return dto;
    }

    @Override
    public UserUpdateResponseDto updateProfile(Authentication authentication,
                                               UserUpdateProfileRequestDto dto) {
        User user = userDetailsService.getUserFromAuthentication(authentication);
        userMapper.updateUser(dto, user);
        return userMapper.toUpdateDto(userRepository.save(user));
    }

    @Override
    public void updatePassword(Authentication authentication, UserUpdatePasswordRequestDto dto) {
        User user = userDetailsService.getUserFromAuthentication(authentication);
        validatePasswordUpdate(user.getPassword(), dto);
        saveNewPassword(user, dto.getConfirmPassword());
    }

    @Override
    public void resetPassword(String email, UserResetPasswordRequestDto dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        validateResetPassword(email);
        saveNewPassword(user, dto.getConfirmPassword());
    }

    @Override
    public void deleteProfile(Authentication authentication) {
        User user = userDetailsService.getUserFromAuthentication(authentication);
        userRepository.delete(user);
    }

    private Role convertsToRole(String roleName) {
        try {
            Role.RoleName value = Role.RoleName.valueOf(roleName.toUpperCase());
            return roleRepository.findByRole(value)
                    .orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleName));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }
    }

    private void ensureNoDuplicateRole(Set<Role> roles, Role roleForUpdate) {
        if (roles.stream().anyMatch(role -> role.equals(roleForUpdate))) {
            throw new DuplicateRoleException("Role already exists: " + roleForUpdate.getRole());
        }
    }

    private void validatePasswordUpdate(String currentPassword, UserUpdatePasswordRequestDto dto) {
        if (!passwordEncoder.matches(dto.getOldPassword(), currentPassword)) {
            throw new PasswordValidationException("The old password is incorrect.");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new PasswordValidationException("The new password "
                    + "and confirmation password do not match.");
        }
        if (currentPassword.equals(dto.getNewPassword())) {
            throw new PasswordValidationException("The new password cannot be "
                    + "the same as the old password.");
        }
    }

    private void validateResetPassword(String email) {
        UserVerification userVerification = userVerificationRepository
                .findByEmailAndType(email, UserVerification.Type.RESET)
                .orElseThrow(() -> new EntityNotFoundException("Verification not found"));
        if (!userVerification.isVerified()
                || !userVerification.getType().equals(UserVerification.Type.RESET)) {
            throw new PasswordValidationException("Password reset validation failed.");
        }
    }

    private void saveNewPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
