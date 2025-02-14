package app.tp136.security;

import app.tp136.exception.EmailSendingException;
import app.tp136.model.UserVerification;
import app.tp136.repository.UserVerificationRepository;
import app.tp136.util.VerificationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final VerificationCodeGenerator verificationCodeGenerator;
    private final UserVerificationRepository userVerificationRepository;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationToEmailForRegistration(String toEmail) {
        sendVerificationToEmail(toEmail,
                UserVerification.Type.VERIFICATION,
                "Registration Verification Code");
    }

    public void sendVerificationToEmailForResetPassword(String toEmail) {
        sendVerificationToEmail(toEmail,
                UserVerification.Type.RESET,
                "Password Reset Verification Code");
    }

    private void sendVerificationToEmail(String toEmail,
                                         UserVerification.Type type,
                                         String subject) {
        String verificationCode = verificationCodeGenerator.generateVerificationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText("Your verification code is: " + verificationCode);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailSendingException("Failed to send verification email. "
                    + "Please try again later.");
        }
        saveVerificationCode(toEmail, verificationCode, type);
    }

    private void saveVerificationCode(String toEmail,
                                      String verificationCode,
                                      UserVerification.Type type) {
        UserVerification userVerification = new UserVerification();
        userVerification.setEmail(toEmail);
        userVerification.setVerificationCode(verificationCode);
        userVerification.setType(type);

        userVerificationRepository.save(userVerification);
    }
}
