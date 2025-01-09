package app.tp136.security;

import app.tp136.exception.EmailVerificationException;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.model.User;
import app.tp136.model.UserVerification;
import app.tp136.repository.UserRepository;
import app.tp136.repository.UserVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final UserVerificationRepository verificationRepository;
    private final UserRepository userRepository;

    public String verifyCodeForRegistration(
            String email, String verificationCode) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
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
            String email, String verificationCode) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
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
