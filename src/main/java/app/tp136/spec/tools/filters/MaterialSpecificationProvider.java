package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MaterialSpecificationProvider implements SpecificationProvider<Product> {
    private static final String MATERIAL_KEY = "material";

    @Override
    public String getKey() {
        return MATERIAL_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] materials) {
        return (root, query, criteriaBuilder)
                -> root.get(MATERIAL_KEY).in(Arrays.asList(materials));
    }
}
