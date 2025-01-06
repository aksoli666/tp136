package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class YearSpecificationProvider implements SpecificationProvider<Product> {
    private static final String YEAR_KEY = "year";

    @Override
    public String getKey() {
        return YEAR_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] years) {
        return (root, query, criteriaBuilder) -> root.get(YEAR_KEY).in(Arrays.asList(years));
    }
}
