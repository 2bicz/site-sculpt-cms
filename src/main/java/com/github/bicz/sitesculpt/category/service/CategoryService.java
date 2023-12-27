package com.github.bicz.sitesculpt.category.service;

import com.github.bicz.sitesculpt.category.dto.CategoryRequest;
import com.github.bicz.sitesculpt.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long categoryId);
    CategoryResponse getCategoryByName(String name);
    Long createCategory(CategoryRequest request);
    Long updateCategory(Long categoryId, CategoryRequest request);
    void deleteCategoryById(Long categoryId);
}
