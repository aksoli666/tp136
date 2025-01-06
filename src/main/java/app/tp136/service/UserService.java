package app.tp136.service;

import app.tp136.dto.UserDto;
import app.tp136.dto.request.UserResetPasswordRequestDto;
import app.tp136.dto.request.UserUpdatePasswordRequestDto;
import app.tp136.dto.request.UserUpdateProfileRequestDto;
import app.tp136.dto.responce.UserUpdateResponseDto;
import org.springframework.security.core.Authentication;

public interface UserService {
    void updateRole(Authentication authentication, String roleName);

    UserDto getProfile(Authentication authentication);

    UserUpdateResponseDto updateProfile(Authentication authentication,
                                        UserUpdateProfileRequestDto dto);

    void updatePassword(Authentication authentication, UserUpdatePasswordRequestDto dto);

    void resetPassword(String email, UserResetPasswordRequestDto dto);

    void deleteProfile(Authentication authentication);
}
