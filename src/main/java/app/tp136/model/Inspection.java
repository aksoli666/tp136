package app.tp136.model;

import app.tp136.converter.StringSetConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "inspections")
@Getter
@Setter
@SQLDelete(sql = "UPDATE inspections SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String userEmail;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    @Convert(converter = StringSetConverter.class)
    @Column(nullable = false, columnDefinition = "longtext")
    private Set<String> photos = new HashSet<>(5);
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum Type {
        ASSESSMENT,
        EXPERTISE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inspection)) {
            return false;
        }
        Inspection that = (Inspection) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(description, that.description)
                .append(userEmail, that.userEmail)
                .append(type, that.type)
                .append(photos, that.photos);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(description)
                .append(userEmail)
                .append(type)
                .append(photos);
        return hcb.toHashCode();
    }
}
