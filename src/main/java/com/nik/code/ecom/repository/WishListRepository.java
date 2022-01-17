package com.nik.code.ecom.repository;

import com.nik.code.ecom.model.User;
import com.nik.code.ecom.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

}
