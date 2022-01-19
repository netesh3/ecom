package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        productService.saveProduct(productDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Product is added"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDTO productDTO, @RequestParam Integer productId){
        productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Product is updated"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProduct(@RequestParam Integer productId){
        productService.removeProduct(productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product is deleted"), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        List<ProductDTO> products = productService.fetchAllProducts(pageNo, pageSize, sortBy);
        return products;
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Integer id){
        return productService.fetchProductById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategoryId(@PathVariable Integer categoryId, @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "id") String sortBy){
        return productService.fetchProductsByCategoryId(categoryId, pageNo, pageSize, sortBy);
    }

    //bulk list of Product

}
