package app.tp136.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "user_verifications")
@Getter
@Setter
@SQLDelete(sql = "UPDATE user_verifications SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String verificationCode;
    @Column(nullable = false)
    private boolean isVerified = false;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum Type {
        VERIFICATION,
        RESET,
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserVerification)) {
            return false;
        }
        UserVerification that = (UserVerification) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(email, that.email)
                .append(verificationCode, that.verificationCode);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(email)
                .append(verificationCode);
        return hcb.toHashCode();
    }
}
