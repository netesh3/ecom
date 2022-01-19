package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.exceptions.CategoryException;
import com.nik.code.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("category")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDTO categoryDTO) throws CategoryException {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Category is added"), HttpStatus.CREATED);
    }

    //updateCategory
    //DeleteCategory
    //bulk list of Category
    //GetCategories
    //GetCategoryById
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