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
    private static final String COUNTRY_KEY_UA = "countryUa";
    private static final String COUNTRY_KEY_ENG = "countryEng";
    private static final String MATERIAL_KEY_UA = "materialUa";
    private static final String MATERIAL_KEY_ENG = "materialEng";
    private static final String NAME_KEY_UA = "nameUa";
    private static final String NAME_KEY_ENG = "nameEng";
    private static final String DESCRIPTION_KEY_UA = "descriptionUa";
    private static final String DESCRIPTION_KEY_ENG = "descriptionEng";
    private static final String YEAR_KEY = "year";
    private final SpecificationProviderManager<Product> specProviderManager;

    @Override
    public Specification<Product> build(ProductSearchParamsDto searchParams) {
        Specification<Product> defaultSpec = Specification.where(null);
        if (searchParams.countryUa() != null && searchParams.countryUa().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(COUNTRY_KEY_UA)
                    .getSpecification(searchParams.countryUa()));
        }
        if (searchParams.countryEng() != null && searchParams.countryEng().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(COUNTRY_KEY_ENG)
                    .getSpecification(searchParams.countryEng()));
        }
        if (searchParams.materialUa() != null && searchParams.materialUa().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(MATERIAL_KEY_UA)
                    .getSpecification(searchParams.materialUa()));
        }
        if (searchParams.materialEng() != null && searchParams.materialEng().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(MATERIAL_KEY_ENG)
                    .getSpecification(searchParams.materialEng()));
        }
        if (searchParams.nameUa() != null && searchParams.nameUa().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(NAME_KEY_UA)
                    .getSpecification(searchParams.nameUa()));
        }
        if (searchParams.nameEng() != null && searchParams.nameEng().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(NAME_KEY_ENG)
                    .getSpecification(searchParams.nameEng()));
        }
        if (searchParams.descriptionUa() != null && searchParams.descriptionUa().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(DESCRIPTION_KEY_UA)
                    .getSpecification(searchParams.descriptionUa()));
        }
        if (searchParams.descriptionEng() != null && searchParams.descriptionEng().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(DESCRIPTION_KEY_ENG)
                    .getSpecification(searchParams.descriptionEng()));
        }
        if (searchParams.year() != null && searchParams.year().length > 0) {
            defaultSpec = defaultSpec.and(specProviderManager
                    .getProvider(YEAR_KEY)
                    .getSpecification(searchParams.year()));
        }
        return defaultSpec;
    }
}
