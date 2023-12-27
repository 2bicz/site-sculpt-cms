package com.github.bicz.sitesculpt.category.dto.mapper;

import com.github.bicz.sitesculpt.category.dto.CategoryRequest;
import com.github.bicz.sitesculpt.category.dto.CategoryResponse;
import com.github.bicz.sitesculpt.category.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper {
    public Category mapCategoryRequestToCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        return category;
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getCategoryId(),
                category.getName()
        );
    }
}
