package com.nik.code.ecom.service;

import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.mapper.ProductMapper;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.repository.ProductRepository;
import com.nik.code.ecom.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SearchRepository searchRepository;

    public List<ProductDTO> searchProducts(String keyword) {
        List<Product> productResult = searchRepository.searchProducts(keyword);
        if(!productResult.isEmpty()){
            List<ProductDTO> productDTOS = new ProductMapper().toDTO(productResult);
            return productDTOS;
        }
        return new ArrayList<>();
    }
}
