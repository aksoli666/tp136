package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PartFromDescrUaSpecificationProvider implements SpecificationProvider<Product> {
    private static final String DESCRIPTION_KEY_UA = "descriptionUa";

    @Override
    public String getKey() {
        return DESCRIPTION_KEY_UA;
    }

    @Override
    public Specification<Product> getSpecification(String[] descrsUa) {
        if (descrsUa == null || descrsUa.length == 0 || descrsUa[0].trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Arrays.stream(descrsUa)
                    .map(descr -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(DESCRIPTION_KEY_UA)),
                            "%" + descr.trim().toLowerCase() + "%"
                    ))
                    .toList();
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
