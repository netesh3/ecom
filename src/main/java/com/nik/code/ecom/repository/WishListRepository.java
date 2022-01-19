package com.nik.code.ecom.repository;

import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;
import com.nik.code.ecom.model.WishList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends PagingAndSortingRepository<WishList, Integer> {
    @Query("from WishList where userDetails = :userDetails and product = :product")
    WishList getUserWishlistByProduct(UserDetails userDetails, Product product);
}
