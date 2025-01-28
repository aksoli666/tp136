package app.tp136.repository;

import app.tp136.model.TransferPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferPaymentRepository extends JpaRepository<TransferPayment, Long> {
}
