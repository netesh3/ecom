package com.nik.code.ecom.repository;

import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.model.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findProductsByCategory(Category category, Pageable pageable);
}
