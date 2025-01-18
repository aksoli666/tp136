package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MaterialUaSpecificationProvider implements SpecificationProvider<Product> {
    private static final String MATERIAL_KEY_UA = "materialUa";

    @Override
    public String getKey() {
        return MATERIAL_KEY_UA;
    }

    @Override
    public Specification<Product> getSpecification(String[] materialsUa) {
        return (root, query, criteriaBuilder)
                -> root.get(MATERIAL_KEY_UA).in(Arrays.asList(materialsUa));
    }
}
