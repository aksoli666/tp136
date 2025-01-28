package app.tp136.security;

import app.tp136.exception.EmailVerificationException;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.model.UserVerification;
import app.tp136.repository.UserVerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final UserVerificationRepository verificationRepository;

    public String verifyCodeForRegistration(String email, String verificationCode) {
        return verifyCode(email, verificationCode, UserVerification.Type.VERIFICATION,
                "registration process");
    }

    public String verifyCodeForResetPassword(String email, String verificationCode) {
        return verifyCode(email, verificationCode, UserVerification.Type.RESET,
                "password reset process");
    }

    private String verifyCode(String email, String verificationCode,
                              UserVerification.Type type, String processName) {
        log.info("Starting email verification for {}. Email: {}", processName, email);

        UserVerification userVerification = getVerificationRecord(email, type, processName);

        if (checkVerificationCode(userVerification, verificationCode)) {
            log.info("Verification code successfully matched for email: {} during {}",
                    email, processName);
            setVerification(userVerification);
            return String.format("Your account has been verified during the %s", processName);
        } else {
            log.warn("Verification code mismatch for email: {} during {}", email, processName);
            throw new EmailVerificationException("Email verification failed. Please try again!");
        }
    }

    private UserVerification getVerificationRecord(String email,
                                                   UserVerification.Type type,
                                                   String processName) {
        return verificationRepository.findByEmailAndType(email, type)
                .orElseThrow(() -> {
                    log.warn("Verification record not found for email: {} during {}",
                            email, processName);
                    return new EntityNotFoundException("Verification not found");
                });
    }

    private boolean checkVerificationCode(UserVerification userVerification,
                                          String verificationCode) {
        log.debug("Checking verification code for email: {}. Expected: {}, Provided: {}",
                userVerification.getEmail(),
                userVerification.getVerificationCode(),
                verificationCode);
        return userVerification.getVerificationCode().equals(verificationCode);
    }

    private void setVerification(UserVerification userVerification) {
        log.info("Setting verification status to true for email: {}", userVerification.getEmail());
        userVerification.setVerified(true);
        verificationRepository.save(userVerification);
        log.info("Verification status updated in the database for email: {}",
                userVerification.getEmail());
    }
}
