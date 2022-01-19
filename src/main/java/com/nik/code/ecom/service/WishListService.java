package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.WishlistBuilder;
import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;
import com.nik.code.ecom.model.WishList;
import com.nik.code.ecom.repository.ProductRepository;
import com.nik.code.ecom.repository.UserDetailsRepository;
import com.nik.code.ecom.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class WishListService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    WishListRepository wishListRepository;

    public Boolean addToWishList(String token, UserProductDTO userProduct) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProduct.getProductId());
        if(!product.isPresent()){
            return false;
        }
        WishList wishList = new WishlistBuilder().withProduct(product.get())
                .withUserDetails(userDetails)
                .withQuantity(1)
                .build();
        wishListRepository.save(wishList);
        return true;
    }

    public Boolean removeFromWishList(String token, UserProductDTO userProducts) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProducts.getProductId());
        if(!product.isPresent()){
            return false;
        }
        WishList wishList = wishListRepository.getUserWishlistByProduct(userDetails, product.get());
        wishListRepository.delete(wishList);
        return true;
    }


}
