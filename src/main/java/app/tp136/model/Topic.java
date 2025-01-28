package app.tp136.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "topics")
@Getter
@Setter
@SQLDelete(sql = "UPDATE topics SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameUa;
    @Column(nullable = false)
    private String nameEng;
    private String descriptionUa;
    private String descriptionEng;
    @ManyToMany(mappedBy = "topics")
    private Set<Discussion> discussions = new HashSet<>();
    @Column(nullable = false)
    private boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Topic)) {
            return false;
        }
        Topic topic = (Topic) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(nameUa, topic.nameUa)
                .append(nameEng, topic.nameEng)
                .append(descriptionUa, topic.descriptionUa)
                .append(descriptionEng, topic.descriptionEng);
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
