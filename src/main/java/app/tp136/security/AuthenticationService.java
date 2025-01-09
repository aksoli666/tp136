package app.tp136.security;

import app.tp136.dto.UserDto;
import app.tp136.dto.request.UserLoginRequestDto;
import app.tp136.dto.request.UserRegisterRequestDto;
import app.tp136.dto.responce.UserLoginResponseDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.exception.RegistrationException;
import app.tp136.mapper.UserMapper;
import app.tp136.model.Role;
import app.tp136.model.User;
import app.tp136.repository.RoleRepository;
import app.tp136.repository.UserRepository;
import app.tp136.service.ShoppingCartService;
import app.tp136.util.AuctionNumberGenerator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ShoppingCartService shoppingCartService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuctionNumberGenerator auctionNumberGenerator;
    private final UserMapper userMapper;

    public UserDto register(UserRegisterRequestDto dto) throws RegistrationException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RegistrationException("Email already exists");
        }

        User user = userMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(roleRepository.findByRole(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"))));
        user.setAuctionNumber(auctionNumberGenerator.generateAuctionNumber());

        userRepository.save(user);
        shoppingCartService.createShoppingCart(user);
        return userMapper.toUserDto(user);
    }

    public UserLoginResponseDto login(UserLoginRequestDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }
}
