package app.tp136.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "categories")
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameUa;
    @Column(nullable = false)
    private String nameEng;
    private String descriptionUa;
    private String descriptionEng;
    @Column(nullable = false)
    private boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category that = (Category) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(nameUa, that.nameUa)
                .append(nameEng, that.nameEng)
                .append(descriptionUa, that.descriptionUa)
                .append(descriptionEng, that.descriptionEng);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(nameUa)
                .append(nameEng)
                .append(descriptionUa)
                .append(descriptionEng);
        return hcb.toHashCode();
    }
}
