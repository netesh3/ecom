package com.nik.code.ecom.dto.userProducts;

import java.util.List;

public class UserProductDTO {
    private Integer productId;
    private Integer Quantity;
    private String token;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productIds) {
        this.productId = productId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
