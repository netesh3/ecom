package com.nik.code.ecom.service;

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

    public Boolean addToWishList(String token, UserProductDTO userProducts) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProducts.getProductId());
        WishList wishList = new WishList(product.get(), userDetails,1,new Date());
        wishListRepository.save(wishList);
        return true;
    }

    public Boolean removeFromWishList(String token, UserProductDTO userProducts) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Optional<Product> product = productRepository.findById(userProducts.getProductId());
        WishList wishList = new WishList(product.get(), userDetails,1,new Date());
        wishListRepository.delete(wishList);
        return true;
    }
}
