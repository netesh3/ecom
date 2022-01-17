package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.repository.CategoryRepository;
import com.nik.code.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        final Optional<Category> category = categoryRepository.findById(productDTO.getCategory_id());
        if(!category.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.CREATED);
        }
        productService.createProduct(productDTO, category.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product is added"), HttpStatus.CREATED);
    }
}
