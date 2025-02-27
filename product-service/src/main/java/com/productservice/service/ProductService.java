package com.productservice.service;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequest request, Integer userId);

    ProductResponse getProductById(Integer id);

    List<ProductResponse> getAllProduct();

    void deleteProduct(Integer id);

    void updateProduct(Integer id, ProductRequest request);

    List<ProductResponse> getAllProductByCategoryId(Integer id);
}
