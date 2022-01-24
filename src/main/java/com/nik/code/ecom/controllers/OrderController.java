package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.repository.OrderRepository;
import com.nik.code.ecom.service.CartService;
import com.nik.code.ecom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam String token, @RequestBody List<UserProductDTO> orderedProductDTOs) throws AuthenticationFailException {

        Boolean isOrderPlaced = orderService.saveOrder(token, orderedProductDTOs);
        if(!isOrderPlaced){
            return new ResponseEntity<>(new ApiResponse(false, "Couldn't placed"), HttpStatus.CREATED);
        }
        cartService.removeProductsFromCart(token, orderedProductDTOs);
        return new ResponseEntity<>(new ApiResponse(true, "Order placed successfully"), HttpStatus.CREATED);
    }

    //ToDo get all orders
    //ToDo get Order for user
}
