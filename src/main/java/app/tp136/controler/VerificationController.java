package app.tp136.controler;

import app.tp136.security.EmailService;
import app.tp136.security.EmailVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Verification", description = "Endpoints for verifying user accounts")
@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class VerificationController {
    private final EmailService emailService;
    private final EmailVerificationService emailVerificationService;

    @Operation(
            summary = "Send verification code to email for registration",
            description = "Sends a verification code to the specified email address "
                    + "to verify the user's email during the registration process."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/send-code-registration")
    public ResponseEntity<String> sendVerificationCodeToEmailForRegistration(
            @RequestParam("to_email")
            @NotBlank String toEmail) {
        emailService.sendVerificationToEmailForRegistration(toEmail);
        return ResponseEntity.ok("Verification code sent");
    }

    @Operation(
            summary = "Send verification code to email for rest password",
            description = "Sends a verification code to the specified email address "
                    + "to verify the user's email during the reset password process."
    )
    @PostMapping("/send-code-rest-pass")
    public ResponseEntity<String> sendVerificationCodeToEmailForResetPassword(
            @RequestParam("to_email")
            @NotBlank String toEmail) {
        emailService.sendVerificationToEmailForResetPassword(toEmail);
        return ResponseEntity.ok("Verification code sent");
    }

    @Operation(
            summary = "Verify email using verification code",
            description = "Verifies the user's email address by checking "
                    + "the provided verification code. "
                    + "If the code is valid, the user's account will be marked as verified."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/verify-code/reg")
    public ResponseEntity<String> verifyCodeForRegistration(Authentication authentication,
                             @RequestParam("verification_code") @NotBlank
                             String verificationCode) {
        String message = emailVerificationService
                .verifyCodeForRegistration(authentication, verificationCode);
        return ResponseEntity.ok(message);
    }

    @Operation(
            summary = "Verify email using verification code",
            description = "Verifies the user's email address by checking "
                    + "the provided verification code. "
                    + "If the code is valid, the user can reset password."
    )
    @PostMapping("/verify-code/reset")
    public ResponseEntity<String> verifyCodeForResetPassword(
            @RequestParam @NotBlank String email,
            @RequestParam("verification_code") @NotBlank String verificationCode) {
        String message = emailVerificationService
                .verifyCodeForResetPassword(email, verificationCode);
        return ResponseEntity.ok(message);
    }
}
