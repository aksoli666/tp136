package app.tp136.service.impl;

import app.tp136.dto.CategoryDto;
import app.tp136.dto.request.CreateCategoryRequestDto;
import app.tp136.dto.request.UpdateCategoryRequestDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.CategoryMapper;
import app.tp136.model.Category;
import app.tp136.repository.CategoryRepository;
import app.tp136.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Can't get category. Id: " + id));
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryMapper.toCategoryDtoPage(
                categoryRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryMapper.toCategory(createCategoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(savedCategory);
    }

    @Transactional
    @Override
    public CategoryDto update(Long id, UpdateCategoryRequestDto updateCategoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get category. Id: " + id));
        categoryMapper.updateCategory(updateCategoryRequestDto, category);
        return categoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Can't delete category. Id: " + id);
        }
    }
}
