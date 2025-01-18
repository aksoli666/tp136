package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameEngSpecificationProvider implements SpecificationProvider<Product> {
    private static final String NAME_KEY_ENG = "nameEng";

    @Override
    public String getKey() {
        return NAME_KEY_ENG;
    }

    @Override
    public Specification<Product> getSpecification(String[] namesEng) {
        if (namesEng == null || namesEng.length == 0 || namesEng[0].trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Arrays.stream(namesEng)
                    .map(name -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(NAME_KEY_ENG)),
                            "%" + name.trim().toLowerCase() + "%"
                    ))
                    .toList();
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
