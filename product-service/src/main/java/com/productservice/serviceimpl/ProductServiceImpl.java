package com.productservice.serviceimpl;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.exception.ResourceNotFoundException;
import com.productservice.model.Category;
import com.productservice.model.Product;
import com.productservice.repository.CategoryRepo;
import com.productservice.repository.ProductRepo;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ModelMapper modelMapper;

    private final CategoryRepo categoryRepo;

    @Override
    public void createProduct(ProductRequest request, Integer userId) {

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Id", request.getCategoryId()));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .userId(userId)
                .build();
        productRepo.save(product);
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "Id", id));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductResponse.class)).toList();
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "Id", id));
        productRepo.delete(product);
    }

    @Override
    public void updateProduct(Integer id, ProductRequest request) {
        Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "Id", id));
        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Id", request.getCategoryId()));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        productRepo.save(product);
    }

    @Override
    public List<ProductResponse> getAllProductByCategoryId(Integer id) {
        List<Product> products = productRepo.findByCategoryId(id);
        return products.stream().map((product) -> modelMapper.map(product, ProductResponse.class)).toList();
    }
}
