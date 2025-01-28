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
@Table(name = "exhibitions")
@Getter
@Setter
@SQLDelete(sql = "UPDATE exhibitions SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titleUa;
    @Column(nullable = false)
    private String titleEng;
    @Column(nullable = false)
    private String contentUa;
    @Column(nullable = false)
    private String contentEng;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Event event;
    @Column(nullable = false)
    private String source;
    @Convert(converter = StringSetConverter.class)
    @Column(nullable = false, columnDefinition = "longtext")
    private Set<String> photos = new HashSet<>(5);
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum Event {
        WAS,
        WILL
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exhibition)) {
            return false;
        }
        Exhibition that = (Exhibition) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(titleUa, that.titleUa)
                .append(titleEng, that.titleEng)
                .append(contentUa, that.contentUa)
                .append(contentEng, that.contentEng)
                .append(event, that.event)
                .append(source, that.source)
                .append(photos, that.photos);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(titleUa)
                .append(titleEng)
                .append(contentUa)
                .append(contentEng)
                .append(event)
                .append(source)
                .append(photos);
        return hcb.toHashCode();
    }
}
