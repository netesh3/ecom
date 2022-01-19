package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        boolean isCreated = productService.createProduct(productDTO);
        if(!isCreated){
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Product is added"), HttpStatus.CREATED);
    }

    //updateProduct
    //DeleteProduct
    //bulk list of Product
    //GetProducts
    //GetProductById
}
