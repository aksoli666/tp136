package app.tp136.security;

import app.tp136.exception.EmailVerificationException;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.model.UserVerification;
import app.tp136.repository.UserVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        UserVerification userVerification = getVerificationRecord(email, type, processName);

        if (checkVerificationCode(userVerification, verificationCode)) {
            setVerification(userVerification);
            return String.format("Your account has been verified during the %s", processName);
        } else {
            throw new EmailVerificationException("Email verification failed. Please try again!");
        }
    }

    private UserVerification getVerificationRecord(String email,
                                                   UserVerification.Type type,
                                                   String processName) {
        return verificationRepository.findByEmailAndType(email, type)
                .orElseThrow(() -> new EntityNotFoundException("Verification not found"));
    }

    private boolean checkVerificationCode(UserVerification userVerification,
                                          String verificationCode) {
        return userVerification.getVerificationCode().equals(verificationCode);
    }

    private void setVerification(UserVerification userVerification) {
        userVerification.setVerified(true);
        verificationRepository.save(userVerification);
    }
}
