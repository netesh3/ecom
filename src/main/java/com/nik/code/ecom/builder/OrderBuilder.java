package com.nik.code.ecom.builder;

import com.nik.code.ecom.model.Order;
import com.nik.code.ecom.model.Product;
import com.nik.code.ecom.model.UserDetails;
import java.util.Date;
import java.util.Optional;

public class OrderBuilder {
    private Product product;
    private UserDetails userDetails;
    private Integer quantity;
    private Date lastUpdatedDate;

    public OrderBuilder() {
        this.lastUpdatedDate = new Date();
    }

    public OrderBuilder withProduct(Product product){
        this.product = product;
        return this;
    }

    public OrderBuilder withUserDetails(UserDetails userDetails){
        this.userDetails = userDetails;
        return this;
    }

    public OrderBuilder withQuantity(Integer quantity){
        this.quantity = quantity;
        return this;
    }

    public Order build(){
        return new Order(this.product, this.userDetails, this.quantity, this.lastUpdatedDate);
    }
}
