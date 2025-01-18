package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameUaSpecificationProvider implements SpecificationProvider<Product> {
    private static final String NAME_KEY_UA = "nameUa";

    @Override
    public String getKey() {
        return NAME_KEY_UA;
    }

    @Override
    public Specification<Product> getSpecification(String[] namesUa) {
        if (namesUa == null || namesUa.length == 0 || namesUa[0].trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Arrays.stream(namesUa)
                    .map(name -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(NAME_KEY_UA)),
                            "%" + name.trim().toLowerCase() + "%"
                    ))
                    .toList();
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
