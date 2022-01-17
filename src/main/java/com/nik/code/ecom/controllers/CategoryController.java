package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.exceptions.CategoryException;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("category")
@RestController
public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @PostMapping("/add")
    public ApiResponse addCategory(@RequestBody Category category)throws CategoryException {
        repository.save(category);
        return new ApiResponse(true, "Category Saved Successfully");
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateCategory(@RequestBody Category category)throws CategoryException {
        repository.save(category);
        return new ApiResponse(true, "Category Updated Successfully");
    }
}
