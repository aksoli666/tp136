package app.tp136.repository;

import app.tp136.model.UserVerification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
    Optional<UserVerification> findByEmail(String email);

    Optional<UserVerification> findByEmailAndType(String email, UserVerification.Type type);
}
