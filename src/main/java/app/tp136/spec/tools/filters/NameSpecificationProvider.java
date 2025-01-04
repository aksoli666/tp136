package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import java.util.Arrays;
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
        return (root, query, criteriaBuilder) -> root.get(NAME_KEY).in(Arrays.asList(names));
    }
}
