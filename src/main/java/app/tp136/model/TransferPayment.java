package app.tp136.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "transfer_payments")
@Getter
@Setter
public class TransferPayment extends Payment {
    @Column(nullable = false)
    private boolean international;
    @Lob
    @Column(nullable = false, columnDefinition = "longtext")
    private String confirmation;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferPayment)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TransferPayment that = (TransferPayment) o;
        EqualsBuilder ebu = new EqualsBuilder()
                .append(international, that.international)
                .append(confirmation, that.confirmation);
        return ebu.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(super.hashCode())
                .append(international)
                .append(confirmation);
        return hcb.toHashCode();
    }
}
