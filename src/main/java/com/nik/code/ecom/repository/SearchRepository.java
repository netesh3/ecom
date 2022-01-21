package com.nik.code.ecom.repository;


import com.nik.code.ecom.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends PagingAndSortingRepository<Product, Integer> {

    @Query(value = "SELECT * FROM Product WHERE :keyword <% concat_ws(' ', name, description, brand) limit 5", nativeQuery = true)
    List<Product> searchProducts(String keyword);
    //https://dev.to/moritzrieger/build-a-fuzzy-search-with-postgresql-2029
}
