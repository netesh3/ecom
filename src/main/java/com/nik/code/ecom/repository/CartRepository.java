package com.nik.code.ecom.repository;

import com.nik.code.ecom.model.Cart;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("from Cart where userDetails = :userDetails and product = :product")
    Cart getUserCartByProduct(UserDetails userDetails, Product product);

    @Query("from Cart where userDetails = :userDetails and product IN :products")
    List<Cart> getUserCartsByProducts(UserDetails userDetails, List<Product> products);
}
