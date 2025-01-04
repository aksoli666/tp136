package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class PartFromDescrSpecificationProvider implements SpecificationProvider<Product> {
    private static final String DESCRIPTION_KEY = "description";

    @Override
    public String getKey() {
        return DESCRIPTION_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] descrs) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Arrays.stream(descrs)
                    .map(descr -> criteriaBuilder
                            .like(root.get(DESCRIPTION_KEY), "%" + descr + "%"))
                    .toList();
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
