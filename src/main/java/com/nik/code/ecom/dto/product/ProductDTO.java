package com.nik.code.ecom.dto.product;

public class ProductDTO {
    private String name;
    private String description;
    private Double regularPrice;
    private Double discountPrice;
    private Integer categoryId;

    public ProductDTO(String name, String description, Double regularPrice, Double discountPrice, Integer categoryId) {
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.categoryId = categoryId;
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

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
