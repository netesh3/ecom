package com.nik.code.ecom.mapper;


import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.utils.DTOMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper implements DTOMapper<ProductDTO, Product> {


    @Override
    public Product toModel(ProductDTO instance) {
        return null;
    }

    @Override
    public ProductDTO toDTO(Product instance) {
        Integer categoryId = instance.getCategory() != null? instance.getCategory().getId() : null;
        return new ProductDTO(instance.getName(), instance.getDescription(), instance.getRegularPrice(), instance.getDiscountPrice(), categoryId);
    }

    @Override
    public List<Product> toModel(List<ProductDTO> instance) {
        return null;
    }

    @Override
    public List<ProductDTO> toDTO(List<Product> instance) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product: instance){
            productDTOS.add(toDTO(product));
        }
        return productDTOS;
    }
}
