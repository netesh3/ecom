package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.ProductBuilder;
import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.repository.CategoryRepository;
import com.nik.code.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public boolean createProduct(ProductDTO productDTO) {

        final Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if(!category.isPresent()){
            return false;
        }
        Product product = new ProductBuilder(productDTO).withCategory(category.get()).build();
        productRepository.save(product);

        return true;
    }

    public Product getProduct(Integer productId) {
        final Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }
}
