package app.tp136.security;

import app.tp136.exception.EmailVerificationException;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.model.User;
import app.tp136.model.UserVerification;
import app.tp136.repository.UserVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final UserVerificationRepository verificationRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public String verifyCodeForRegistration(
            Authentication authentication, String verificationCode) {
        User user = customUserDetailsService
                .getUserFromAuthentication(authentication);
        UserVerification userVerification = verificationRepository
                .findByEmailAndType(user.getEmail(), UserVerification.Type.VERIFICATION)
                .orElseThrow(() -> new EntityNotFoundException("Verification not found"));
        if (checkVerificationCode(userVerification, verificationCode)) {
            setVerification(userVerification);
            return "Your account has been verified during the registration process";
        }
        throw new EmailVerificationException("Email verification failed. Please try again!");
    }

    public String verifyCodeForResetPassword(
            Authentication authentication, String verificationCode) {
        User user = customUserDetailsService
                .getUserFromAuthentication(authentication);
        UserVerification userVerification = verificationRepository
                .findByEmailAndType(user.getEmail(), UserVerification.Type.RESET)
                .orElseThrow(() -> new EntityNotFoundException("Verification not found"));
        if (checkVerificationCode(userVerification, verificationCode)) {
            setVerification(userVerification);
            return "Your account has been verified during the reset password process";
        }
        throw new EmailVerificationException("Email verification failed. Please try again!");
    }

    private boolean checkVerificationCode(
            UserVerification userVerification, String verificationCode) {
        return userVerification.getVerificationCode().equals(verificationCode);
    }

    private void setVerification(UserVerification userVerification) {
        userVerification.setVerified(true);
        verificationRepository.save(userVerification);
    }
}
