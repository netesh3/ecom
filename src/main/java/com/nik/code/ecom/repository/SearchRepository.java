package com.nik.code.ecom.repository;


import com.nik.code.ecom.model.Product;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends PagingAndSortingRepository<Product, Integer> {

    /*
        TODO
        use Soundex and Levenshtein algorithm for searching
     */
    @Query(value = "SELECT * FROM Product WHERE :keyword <% concat_ws(' ', name, description, brand) limit 5", nativeQuery = true)
    List<Product> searchProducts(String keyword);

    /*
        Reference https://dev.to/moritzrieger/build-a-fuzzy-search-with-postgresql-2029
     */

}
