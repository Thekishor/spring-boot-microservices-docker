package com.productservice.controller;

import com.productservice.dto.CategoryRequest;
import com.productservice.dto.CategoryResponse;
import com.productservice.exception.UnauthorizedAccessException;
import com.productservice.payload.ApiResponse;
import com.productservice.repository.CategoryRepo;
import com.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryRepo categoryRepo;

    @PostMapping
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryRequest request,
            @RequestHeader("id") Integer userId,
            @RequestHeader("role") String role){

        if (!"ADMIN".equals(role)){
            throw new UnauthorizedAccessException("Only Admin can create category!");
        }

        if (!categoryRepo.existsById(userId)){
            throw new UnauthorizedAccessException("User Not Found with Id");
        }
        else {
            categoryService.createCategory(request);
            return new ResponseEntity<>("Category Created", HttpStatus.CREATED);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("categoryId") Integer id){
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
        List<CategoryResponse> categoryResponses = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable("categoryId") Integer id,
            @RequestHeader("Id")@NotNull Integer userId,
            @RequestHeader("role")@NotNull String userRole)
    {
        if(!"ADMIN".equals(userRole)){
            throw new UnauthorizedAccessException("Only Admin can delete the categories!");
        }

        if (!categoryRepo.existsById(userId)){
            throw new UnauthorizedAccessException("User Not found with Id");
        }
        else {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("categoryId") Integer id,
            @Valid @RequestBody CategoryRequest request,
            @RequestHeader("Id")@NotNull Integer userId,
            @RequestHeader("role")@NotNull String userRole)
    {
        if(!"ADMIN".equals(userRole)){
            throw new UnauthorizedAccessException("Only Admin can update the categories!");
        }

        if (!categoryRepo.existsById(userId)){
            throw new UnauthorizedAccessException("User Not found with Id");
        }
        else {
            categoryService.updateCategory(id, request);
            return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
        }
    }
}
