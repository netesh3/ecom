package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.model.WishList;
import com.nik.code.ecom.service.AuthenticationService;
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

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        WishList wishList = new WishList(user, product);
        wishListService.createWishList(wishList);
        ApiResponse apiResponse = new ApiResponse(true, "Added to WishList");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
