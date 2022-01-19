package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse> addToCart(@RequestParam String token, @RequestBody UserProductDTO userProduct) throws AuthenticationFailException {

        Boolean isAdded = cartService.addToCart(token, userProduct);
        if(!isAdded){
            return new ResponseEntity<>(new ApiResponse(false, "Cannot be added"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Product added to Cart"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> removeFromCart(@RequestParam String token, @RequestBody UserProductDTO userProduct) throws AuthenticationFailException {
        Boolean isRemoved = cartService.removeFromCart(token, userProduct);
        if(!isRemoved){
            return new ResponseEntity<>(new ApiResponse(false, "Cannot be Removed"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Product removed from cart"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateCart(@RequestParam String token, @RequestBody UserProductDTO userProduct) throws AuthenticationFailException {
        Boolean isUpdated = cartService.updateCart(token, userProduct);
        if(!isUpdated){
            return new ResponseEntity<>(new ApiResponse(false, "Cannot be updated"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Product updated in cart"), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductDTO> getProductsFromCart(@RequestParam String token) throws AuthenticationFailException {
        return cartService.fetchProductsFromCart(token);
    }
}
