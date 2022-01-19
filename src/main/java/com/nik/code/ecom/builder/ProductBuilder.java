package com.nik.code.ecom.builder;

import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.model.Product;

public class ProductBuilder {
    private String name;
    private String description;
    private Double regularPrice;
    private Double discountPrice;
    private Category category;

    public ProductBuilder(ProductDTO product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.regularPrice = product.getRegularPrice();
        this.discountPrice = product.getDiscountPrice();
    }

    public ProductBuilder withCategory(Category category){
        this.category = category;
        return this;
    }

    public Product build(){
        return new Product(this.name, this.description, this.regularPrice, this.discountPrice, this.category);
    }
}
