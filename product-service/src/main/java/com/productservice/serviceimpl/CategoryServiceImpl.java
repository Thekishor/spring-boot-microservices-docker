package com.productservice.serviceimpl;

import com.productservice.dto.CategoryRequest;
import com.productservice.dto.CategoryResponse;
import com.productservice.exception.ResourceNotFoundException;
import com.productservice.model.Category;
import com.productservice.repository.CategoryRepo;
import com.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final ModelMapper modelMapper;

    @Override
    public void createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        categoryRepo.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", id));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map((category) -> modelMapper.map(category, CategoryResponse.class)).toList();
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Id",id));
        categoryRepo.delete(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryRequest request) {
        Category category = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Id",id));
        Category categoryUpdate = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        categoryRepo.save(category);
    }
}
