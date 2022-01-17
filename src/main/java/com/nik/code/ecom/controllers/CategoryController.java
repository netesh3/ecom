package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.exceptions.CategoryException;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.repository.CategoryRepository;
import com.nik.code.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("category")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ApiResponse addCategory(@RequestBody CategoryDTO categoryDTO)throws CategoryException {
        categoryService.createCategory(categoryDTO);
        return new ApiResponse(true, "Category Saved Successfully");
    }
}
