package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.CartBuilder;
import com.nik.code.ecom.builder.OrderBuilder;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.dto.userProducts.UserProductDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.mapper.ProductMapper;
import com.nik.code.ecom.model.Cart;
import com.nik.code.ecom.model.Order;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;
import com.nik.code.ecom.repository.CartRepository;
import com.nik.code.ecom.repository.OrderRepository;
import com.nik.code.ecom.repository.ProductRepository;
import com.nik.code.ecom.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    public Boolean saveOrder(String token, List<UserProductDTO> orderedProductDTOs) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        List<Order> orders = new ArrayList<>();
        for(UserProductDTO productDTO: orderedProductDTOs){
            Optional<Product> product = productRepository.findById(productDTO.getProductId());
            if(!product.isPresent()){
                continue;
            }
            Order order = new OrderBuilder().withUserDetails(userDetails)
                    .withProduct(product.get())
                    .withQuantity(productDTO.getQuantity())
                    .build();
            orders.add(order);
        }
        orderRepository.saveAll(orders);
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
