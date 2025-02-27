package com.productservice.controller;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.exception.UnauthorizedAccessException;
import com.productservice.payload.ApiResponse;
import com.productservice.repository.ProductRepo;
import com.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductRepo productRepo;

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductRequest productRequest,
            @RequestHeader("Id")@NotNull Integer userId,
            @RequestHeader("role")@NotNull String userRole){

        if(!"ADMIN".equals(userRole)){
            throw new UnauthorizedAccessException("Only Admin can create products!");
        }

        if (!productRepo.existsById(userId)){
            throw new UnauthorizedAccessException("User Not found with Id");
        }
        else {
            productService.createProduct(productRequest,userId);
            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Integer id){
        ProductResponse productResponse = productService.getProductById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> productResponses = productService.getAllProduct();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable("productId") Integer id,
            @RequestHeader("Id")@NotNull Integer userId,
            @RequestHeader("role")@NotNull String userRole)
    {
        if(!"ADMIN".equals(userRole)){
            throw new UnauthorizedAccessException("Only Admin can delete the products!");
        }

        if (!productRepo.existsById(userId)){
            throw new UnauthorizedAccessException("User Not found with Id");
        }
        else {
            productService.deleteProduct(id);
            return new ResponseEntity<>(new ApiResponse("Product Deleted Successfully", true), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("productId") Integer id,
            @Valid @RequestBody ProductRequest request,
            @RequestHeader("Id")@NotNull Integer userId,
            @RequestHeader("role")@NotNull String userRole)
    {
        if(!"ADMIN".equals(userRole)){
            throw new UnauthorizedAccessException("Only Admin can update the products!");
        }

        if (!productRepo.existsById(userId)){
            throw new UnauthorizedAccessException("User Not found with Id");
        }
        else {
            productService.updateProduct(id, request);
            return new ResponseEntity<>("Product Updated Successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getAllProductByCategoryId(@PathVariable("categoryId") Integer id){
        List<ProductResponse> productResponses = productService.getAllProductByCategoryId(id);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }
}