package com.nik.code.ecom.repository;

import com.nik.code.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Override
    List<Product> findAllById(Iterable<Integer> ids);
}
