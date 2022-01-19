package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.service.CategoryService;
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
        return new ResponseEntity<>(new ApiResponse(true, "Category is updated"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam(name = "categoryId") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse(true, "Category is deleted"), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoryDTO> getCategories(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        List<CategoryDTO> categories = categoryService.getAllCategories(pageNo, pageSize, sortBy);
        return categories;
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Integer id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return category;
    }
}
