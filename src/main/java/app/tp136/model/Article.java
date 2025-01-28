package app.tp136.model;

import app.tp136.converter.StringSetConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    @Convert(converter = StringSetConverter.class)
    @Column(nullable = false, columnDefinition = "longtext")
    private Set<String> photos = new HashSet<>(5);
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "articles_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
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
                .append(published, that.published)
                .append(photos, that.photos);
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
