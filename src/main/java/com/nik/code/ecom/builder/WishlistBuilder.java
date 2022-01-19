package com.nik.code.ecom.builder;

import com.nik.code.ecom.model.*;

import java.util.Date;
import java.util.List;

public class WishlistBuilder {
    private Product product;
    private UserDetails userDetails;
    private Integer quantity;
    private Date lastUpdatedDate;

    public WishlistBuilder() {
        this.lastUpdatedDate = new Date();
    }

    public WishlistBuilder withProduct(Product product){
        this.product = product;
        return this;
    }

    public WishlistBuilder withUserDetails(UserDetails userDetails){
        this.userDetails = userDetails;
        return this;
    }

    public WishlistBuilder withQuantity(Integer quantity){
        this.quantity = quantity;
        return this;
    }

    public WishList build(){
        return new WishList(this.product, this.userDetails, this.quantity, this.lastUpdatedDate);
    }
}
