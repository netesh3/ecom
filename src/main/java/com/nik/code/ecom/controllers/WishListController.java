package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wishlist")
@RestController
public class WishListController {

    @Autowired
    WishListService wishListService;

    @PostMapping
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody UserProductDTO userProduct) throws AuthenticationFailException {
        Boolean isAdded = wishListService.addToWishList(userProduct);
        if(!isAdded){
            return new ResponseEntity<>(new ApiResponse(false, "Cannot be added"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Products added to wishlist"), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> removeFromWishList(@RequestBody UserProductDTO userProduct) throws AuthenticationFailException {}
}
