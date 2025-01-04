package app.tp136.spec.tools.impl;

import app.tp136.model.Product;
import app.tp136.spec.tools.SpecificationProvider;
import app.tp136.spec.tools.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSpecificationProviderManager implements SpecificationProviderManager<Product> {
    private final List<SpecificationProvider<Product>> specificationProviders;

    @Override
    public SpecificationProvider<Product> getProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No specification provider found for key: " + key));
    }
}
