package app.tp136.spec.tools;

import app.tp136.dto.ProductSearchParamsDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(ProductSearchParamsDto dto);
}
