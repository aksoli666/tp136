package app.tp136.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cash_payments")
@Getter
@Setter
public class CashPayment extends Payment {
}
