package com.nik.code.ecom.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private Double regularPrice;

    private Double discountPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> userCartProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> userOrderedProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> userWishlistProducts;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    private String brand;

    @NotNull
    @NotBlank
    private Integer quantity;

    public Product() {
    }

    public Product(String name, String description, Double regularPrice, Double discountPrice, Category category, String brand, Integer quantity) {
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regular_price) {
        this.regularPrice = regularPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discount_price) {
        this.discountPrice = discountPrice;
    }

    public List<Cart> getUserCartProducts() {
        return userCartProducts;
    }

    public void setUserCartProducts(List<Cart> userCartProducts) {
        this.userCartProducts = userCartProducts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Cart> getUserOrderedProducts() {
        return userOrderedProducts;
    }

    public void setUserOrderedProducts(List<Cart> userOrderedProducts) {
        this.userOrderedProducts = userOrderedProducts;
    }

    public List<Cart> getUserWishlistProducts() {
        return userWishlistProducts;
    }

    public void setUserWishlistProducts(List<Cart> userWishlistProducts) {
        this.userWishlistProducts = userWishlistProducts;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
