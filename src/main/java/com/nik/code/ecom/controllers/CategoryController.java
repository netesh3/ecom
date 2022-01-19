package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.service.CategoryService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Category is added"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDTO categoryDTO, @RequestParam(name = "categoryId") Integer categoryId) {
        categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(new ApiResponse(true, "Category is added"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam(name = "categoryId") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse(true, "Category is added"), HttpStatus.CREATED);
    }

    //TODO pagination and sorting
    @GetMapping
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return categories;
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Integer id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return category;
    }
}


//Cart Controller
//addToCart
//removeFromCart
//updateCart[Quantity]
//GetCartFromToken

//OrderController
//placeOrder-->removeFromCart
//public response placeOrdeder(Product, Usertoen)
//{
//    //address
//    orderservice.placeorder();
//}


//SearchController
//search[Product]--> Pagination