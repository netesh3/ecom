package com.nik.code.ecom.service;

import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setProduct_url(productDTO.getProduct_url());
        product.setRegular_price(productDTO.getRegular_price());
        product.setDiscount_price(productDTO.getDiscount_price());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(category);
        productRepository.save(product);
    }

    public Product getProduct(Integer product_id) {
        Optional<Product> product = productRepository.findById(product_id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }
}
