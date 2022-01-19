package com.nik.code.ecom.builder;

import com.nik.code.ecom.model.Cart;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;

import java.util.Date;

public class CartBuilder {
    private Product product;
    private UserDetails userDetails;
    private Integer quantity;
    private Date lastUpdatedDate;

    public CartBuilder() {
        this.lastUpdatedDate = new Date();
    }

    public CartBuilder withProduct(Product product){
        this.product = product;
        return this;
    }

    public CartBuilder withUserDetails(UserDetails userDetails){
        this.userDetails = userDetails;
        return this;
    }

    public CartBuilder withQuantity(Integer quantity){
        this.quantity = quantity;
        return this;
    }

    public Cart build(){
        return new Cart(this.product, this.userDetails, this.quantity, this.lastUpdatedDate);
    }
}
