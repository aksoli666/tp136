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
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String content;
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
                .append(title, that.title)
                .append(author, that.author)
                .append(content, that.content)
                .append(published, that.published);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(title)
                .append(author)
                .append(content)
                .append(published);
        return hcb.toHashCode();
    }
}
