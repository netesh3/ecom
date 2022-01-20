package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.ProductBuilder;
import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.mapper.ProductMapper;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.repository.CategoryRepository;
import com.nik.code.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public void saveProduct(ProductDTO productDTO) {
        final Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if(category.isPresent()){
            Product product = new ProductBuilder(productDTO).withCategory(category.get()).build();
            productRepository.save(product);
        }
    }

    public void updateProduct(Integer productId, ProductDTO productDTO){
        final Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if(category.isPresent()) {
            Optional<Product> product = productRepository.findById(productId);
            if(product.isPresent()){
                Product productEntity = product.get();
                productEntity.setName(productDTO.getName());
                productEntity.setDescription(productDTO.getDescription());
                productEntity.setRegularPrice(productDTO.getRegularPrice());
                productEntity.setDiscountPrice(productDTO.getDiscountPrice());
                productEntity.setCategory(category.get());
                productEntity.setBrand(productDTO.getBrand());
                productEntity.setQuantity(productDTO.getQuantity());
                productRepository.save(productEntity);
            }
        }
    }

    public void removeProduct(Integer productId){
        productRepository.deleteById(productId);
    }

    public List<ProductDTO> fetchAllProducts(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> productPagedResult = productRepository.findAll(paging);
        if (productPagedResult.hasContent()) {
            List<Product> products = productPagedResult.getContent();
            List<ProductDTO> productDTOS = new ProductMapper().toDTO(products);
            return productDTOS;
        } else {
            return new ArrayList<ProductDTO>();
        }
    }

    public ProductDTO fetchProductById(Integer productId){
        final Optional<Product> product = productRepository.findById(productId);
        ProductDTO productDTO = null;
        if(product.isPresent()){
            productDTO = new ProductMapper().toDTO(product.get());
        }
        return productDTO;
    }

    public List<ProductDTO> fetchProductsByCategoryId(Integer categoryId, Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        final Optional<Category> category = categoryRepository.findById(categoryId);
        if(!category.isPresent()){
            return new ArrayList<ProductDTO>();
        }
        Page<Product> productPagedResult = productRepository.findProductsByCategory(category.get(), paging);
        if (productPagedResult.hasContent()) {
            List<Product> products = productPagedResult.getContent();
            List<ProductDTO> productDTOS = new ProductMapper().toDTO(products);
            return productDTOS;
        } else {
            return new ArrayList<ProductDTO>();
        }
    }
}
