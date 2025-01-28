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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "products")
@Getter
@Setter
@SQLDelete(sql = "UPDATE products SET is_deleted=true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameUa;
    @Column(nullable = false)
    private String nameEng;
    @Column(nullable = false)
    private String descriptionUa;
    @Column(nullable = false)
    private String descriptionEng;
    private String countryUa;
    private String countryEng;
    private int year;
    private String materialUa;
    private String materialEng;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int inventory;
    @Column(nullable = false)
    private LocalDate publicationDate;
    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Category> categories = new HashSet<>();
    @Convert(converter = StringSetConverter.class)
    @Column(nullable = false, columnDefinition = "longtext")
    private Set<String> photos = new HashSet<>(5);
    @Column(nullable = false)
    private boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product that = (Product) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(nameUa, that.nameUa)
                .append(nameEng, that.nameEng)
                .append(descriptionUa, that.descriptionUa)
                .append(descriptionEng, that.descriptionEng)
                .append(countryUa, that.countryUa)
                .append(countryEng, that.countryEng)
                .append(year, that.year)
                .append(materialUa, that.materialUa)
                .append(materialEng, that.materialEng)
                .append(price, that.price)
                .append(inventory, that.inventory)
                .append(publicationDate, that.publicationDate)
                .append(photos, that.photos);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(nameUa)
                .append(nameEng)
                .append(descriptionUa)
                .append(descriptionEng)
                .append(countryUa)
                .append(countryEng)
                .append(year)
                .append(materialUa)
                .append(materialEng)
                .append(price)
                .append(inventory)
                .append(publicationDate)
                .append(photos);
        return hcb.toHashCode();
    }
}
