package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<Product> {
    private static final String NAME_KEY = "name";

    @Override
    public String getKey() {
        return NAME_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] names) {
        if (names == null || names.length == 0 || names[0].trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Arrays.stream(names)
                    .map(name -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(NAME_KEY)),
                            "%" + name.trim().toLowerCase() + "%"
                    ))
                    .toList();
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
