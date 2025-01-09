package app.tp136.controler;

import app.tp136.dto.UserDto;
import app.tp136.dto.request.UserResetPasswordRequestDto;
import app.tp136.dto.request.UserUpdatePasswordRequestDto;
import app.tp136.dto.request.UserUpdateProfileRequestDto;
import app.tp136.dto.responce.UserUpdateResponseDto;
import app.tp136.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User management", description = "Endpoints for managing user information")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Assign a new role to a user",
            description = "Assigns a new role (ROLE_MANAGER, ROLE_CUSTOMER) to the user."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @PutMapping("/upd-role")
    public void updateRole(Authentication authentication,
                           @RequestParam("email") String email,
                           @RequestParam("role_name") String roleName) {
        userService.updateRole(authentication, email, roleName);
    }

    @Operation(
            summary = "Retrieve the authenticated user's profile",
            description = "Fetches the profile information of the authenticated user."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/me")
    public UserDto getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }

    @Operation(
            summary = "Update the authenticated user's profile",
            description = "Updates the profile information of the authenticated user."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/me/upd-profile")
    public UserUpdateResponseDto updateProfile(
            Authentication authentication,
            @Valid @RequestBody UserUpdateProfileRequestDto dto) {
        return userService.updateProfile(authentication, dto);
    }

    @Operation(
            summary = "Update the authenticated user's password",
            description = "Allows the authenticated user to update their password."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/me/upd-pass")
    public void updatePassword(Authentication authentication,
                               @Valid @RequestBody UserUpdatePasswordRequestDto dto) {
        userService.updatePassword(authentication, dto);
    }

    @Operation(
            summary = "Reset the authenticated user's password",
            description = "Allows the authenticated user to reset their password securely."
    )
    @PutMapping("/me/reset-pass")
    public void resetPassword(@RequestParam @NotBlank String email,
                              @Valid @RequestBody UserResetPasswordRequestDto dto) {
        userService.resetPassword(email, dto);
    }

    @Operation(
            summary = "Delete the authenticated user's profile",
            description = "Permanently deletes the authenticated user's profile."
    )
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @DeleteMapping("/me/delete")
    public void deleteProfile(Authentication authentication) {
        userService.deleteProfile(authentication);
    }
}
