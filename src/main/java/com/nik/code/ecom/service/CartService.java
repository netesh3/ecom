package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.CartBuilder;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.mapper.ProductMapper;
import com.nik.code.ecom.model.*;
import com.nik.code.ecom.repository.CartRepository;
import com.nik.code.ecom.repository.ProductRepository;
import com.nik.code.ecom.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    CartRepository cartRepository;

    public Boolean addToCart(String token, UserProductDTO userProduct) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProduct.getProductId());
        if(!product.isPresent()){
            return false;
        }
        Cart cart = cartRepository.getUserCartByProduct(userDetails, product.get());
        if(Objects.nonNull(cart)){
            cart.setQuantity(cart.getQuantity() + userProduct.getQuantity());
        }else {
            cart = new CartBuilder().withProduct(product.get())
                    .withUserDetails(userDetails)
                    .withQuantity(userProduct.getQuantity())
                    .build();
        }
        cartRepository.save(cart);
        return true;
    }

    public Boolean removeFromCart(String token, UserProductDTO userProduct) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProduct.getProductId());
        if(!product.isPresent()){
            return false;
        }
        Cart cart = cartRepository.getUserCartByProduct(userDetails, product.get());
        cartRepository.delete(cart);
        return true;
    }

    public void removeProductsFromCart(String token, List<UserProductDTO> orderedProductDTOs) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        List<Integer> productIds = new ArrayList<>();
        for(UserProductDTO productDTO: orderedProductDTOs){
            productIds.add(productDTO.getProductId());
        }
        List<Product> products = (List<Product>) productRepository.findAllById(productIds);

        List<Cart> carts = cartRepository.getUserCartsByProducts(userDetails, products);
        cartRepository.deleteAll(carts);
    }

    public Boolean updateCart(String token, UserProductDTO userProduct) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProduct.getProductId());
        if(!product.isPresent()){
            return false;
        }
        Cart cart = cartRepository.getUserCartByProduct(userDetails, product.get());
        cart.setQuantity(userProduct.getQuantity());
        cartRepository.save(cart);
        return true;
    }

    public List<ProductDTO> fetchProductsFromCart(String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        List<Cart> carts = userDetails.getUserCartProducts();
        List<Product> products = new ArrayList<>();
        for (Cart cart: carts) {
            products.add(cart.getProduct());
        }
        List<ProductDTO> productDTOS = new ProductMapper().toDTO(products);
        return productDTOS;
    }
}
