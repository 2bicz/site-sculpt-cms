package com.github.bicz.sitesculpt.category.service.impl;

import com.github.bicz.sitesculpt.category.dto.CategoryRequest;
import com.github.bicz.sitesculpt.category.dto.CategoryResponse;
import com.github.bicz.sitesculpt.category.dto.mapper.CategoryDtoMapper;
import com.github.bicz.sitesculpt.category.model.Category;
import com.github.bicz.sitesculpt.category.repository.CategoryRepository;
import com.github.bicz.sitesculpt.category.service.CategoryService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryRequestValidator requestValidator;
    private final CategoryDtoMapper mapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        ArrayList<CategoryResponse> response = new ArrayList<>();
        ArrayList<Category> categories = (ArrayList<Category>) categoryRepository.findAll();

        for (Category category : categories) {
            if (Objects.nonNull(category)) {
                response.add(mapper.mapCategoryToCategoryResponse(category));
            }
        }

        return response;
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) throws RequestNotCorrectException {
        if (Objects.isNull(categoryId)) {
            throw new RequestNotCorrectException("Provided category id is empty");
        }
        return mapper.mapCategoryToCategoryResponse(obtainExistingCategory(categoryId));
    }

    @Override
    public CategoryResponse getCategoryByName(String name) throws RequestNotCorrectException {
        if (Objects.isNull(name)) {
            throw new RequestNotCorrectException("Provided name is empty");
        }
        return mapper.mapCategoryToCategoryResponse(obtainExistingCategory(name));
    }

    @Override
    public Long createCategory(CategoryRequest request) {
        requestValidator.validateCategoryRequest(request);
        return categoryRepository.save(mapper.mapCategoryRequestToCategory(request)).getCategoryId();
    }

    @Override
    public Long updateCategory(Long categoryId, CategoryRequest request) throws RequestNotCorrectException {
        if (Objects.isNull(categoryId)) {
            throw new RequestNotCorrectException("Provided category id is empty");
        }
        requestValidator.validateCategoryRequest(request);

        Category category = obtainExistingCategory(categoryId);
        Category categoryUpdate = mapper.mapCategoryRequestToCategory(request);

        category.setName(categoryUpdate.getName());

        return categoryRepository.save(category).getCategoryId();
    }

    @Override
    public void deleteCategoryById(Long categoryId) throws RequestNotCorrectException {
        if (Objects.isNull(categoryId)) {
            throw new RequestNotCorrectException("Provided category id is empty");
        }
        categoryRepository.deleteById(categoryId);
    }

    private Category obtainExistingCategory(Long categoryId) throws ResourceNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Category with id %d does not exist", categoryId));
        }
        return optionalCategory.get();
    }

    private Category obtainExistingCategory(String name) throws ResourceNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Category with name %s does not exist", name));
        }
        return optionalCategory.get();
    }
}
