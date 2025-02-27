package com.productservice.service;

import com.productservice.dto.CategoryRequest;
import com.productservice.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryRequest request);

    CategoryResponse getCategoryById(Integer id);

    List<CategoryResponse> getAllCategory();

    void deleteCategory(Integer id);

    void updateCategory(Integer id, CategoryRequest request);
}
