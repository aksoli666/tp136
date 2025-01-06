package app.tp136.spec.tools.impl;

import app.tp136.dto.ProductSearchParamsDto;
import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationBuilder;
import app.tp136.spec.tools.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSpecificationBuilder implements SpecificationBuilder<Product> {
    private static final String COUNTRY_KEY = "country";
    private static final String MATERIAL_KEY = "material";
    private static final String NAME_KEY = "name";
    private static final String DESCRIPTION_KEY = "description";
    private static final String YEAR_KEY = "year";
    private final SpecificationProviderManager<Product> specProviderManager;

    @Override
    public Specification<Product> build(ProductSearchParamsDto searchParams) {
        Specification<Product> defaultSpec = Specification.where(null);
        if (searchParams.country() != null && searchParams.country().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(COUNTRY_KEY)
                    .getSpecification(searchParams.country()));
        }
        if (searchParams.material() != null && searchParams.material().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(MATERIAL_KEY)
                    .getSpecification(searchParams.material()));
        }
        if (searchParams.name() != null && searchParams.name().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(NAME_KEY)
                    .getSpecification(searchParams.name()));
        }
        if (searchParams.description() != null && searchParams.description().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(DESCRIPTION_KEY)
                    .getSpecification(searchParams.description()));
        }
        if (searchParams.year() != null && searchParams.year().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(YEAR_KEY)
                    .getSpecification(searchParams.year()));
        }
        return defaultSpec;
    }
}
