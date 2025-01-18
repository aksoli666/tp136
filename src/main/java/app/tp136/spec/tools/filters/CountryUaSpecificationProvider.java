package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CountryUaSpecificationProvider implements SpecificationProvider<Product> {
    private static final String COUNTRY_KEY_UA = "countryUa";

    @Override
    public String getKey() {
        return COUNTRY_KEY_UA;
    }

    @Override
    public Specification<Product> getSpecification(String[] countriesUa) {
        return (root, query, criteriaBuilder)
                -> root.get(COUNTRY_KEY_UA).in(Arrays.asList(countriesUa));
    }
}
