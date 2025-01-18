package app.tp136.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "articles")
@Getter
@Setter
@SQLDelete(sql = "UPDATE articles SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titleUa;
    @Column(nullable = false)
    private String titleEng;
    @Column(nullable = false)
    private String authorUa;
    @Column(nullable = false)
    private String authorEng;
    @Column(nullable = false)
    private String contentUa;
    @Column(nullable = false)
    private String contentEng;
    @Column(nullable = false)
    private LocalDateTime published;
    @Column(nullable = false)
    private boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        Article that = (Article) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(titleUa, that.titleUa)
                .append(authorUa, that.authorUa)
                .append(authorEng, that.authorEng)
                .append(contentUa, that.contentUa)
                .append(contentEng, that.contentEng)
                .append(published, that.published);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(titleUa)
                .append(titleEng)
                .append(authorUa)
                .append(authorEng)
                .append(contentUa)
                .append(contentEng)
                .append(published);
        return hcb.toHashCode();
    }
}
