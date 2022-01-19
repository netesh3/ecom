package com.nik.code.ecom.service;

import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;
import com.nik.code.ecom.model.WishList;
import com.nik.code.ecom.repository.ProductRepository;
import com.nik.code.ecom.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WishListService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationService authenticationService;

    public boolean addToWishList(UserProductDTO userProducts) throws AuthenticationFailException {
//        String token = userProducts.getToken();
//        List<Integer> productIds = userProducts.getProductIds();
//        authenticationService.authenticate(token);
//        UserDetails userDetails = authenticationService.getUserDetails(token);
//        List<Product> products = productRepository.findAllById(productIds);
//        WishList wishList = userDetails.getWishlist();
//        if(wishList.getProducts() != null && products !=null) {
//            products = Stream.concat(products.stream(), wishList.getProducts().stream()).collect(Collectors.toList());
//        }
//        wishList.setProducts(products);
//        userDetails.setWishlist(wishList);
//        userDetailsRepository.save(userDetails);
        return true;
    }
}
