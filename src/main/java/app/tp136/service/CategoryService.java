package app.tp136.service;

import app.tp136.dto.CategoryDto;
import app.tp136.dto.request.CreateCategoryRequestDto;
import app.tp136.dto.request.UpdateCategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto get(Long id);

    Page<CategoryDto> findAll(Pageable pageable);

    CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryDto update(Long id, UpdateCategoryRequestDto updateCategoryRequestDto);

    void delete(Long id);
}
