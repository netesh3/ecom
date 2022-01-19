package com.nik.code.ecom.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_ordered_products")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private Date lastUpdatedDate;

    public Order() {
    }

    public Order(Product product, UserDetails userDetails, Integer quantity, Date lastUpdatedDate) {
        this.product = product;
        this.userDetails = userDetails;
        this.quantity = quantity;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getId() {
        return id;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
