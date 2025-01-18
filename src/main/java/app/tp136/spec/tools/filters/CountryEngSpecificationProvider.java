package app.tp136.spec.tools.filters;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CountryEngSpecificationProvider implements SpecificationProvider<Product> {
    private static final String COUNTRY_KEY_ENG = "countryEng";

    @Override
    public String getKey() {
        return COUNTRY_KEY_ENG;
    }

    @Override
    public Specification<Product> getSpecification(String[] countriesEng) {
        return (root, query, criteriaBuilder)
                -> root.get(COUNTRY_KEY_ENG).in(Arrays.asList(countriesEng));
    }
}
