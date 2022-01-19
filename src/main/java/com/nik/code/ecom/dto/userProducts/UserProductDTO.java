package com.nik.code.ecom.dto.userProducts;

import java.util.List;

public class UserProductDTO {
    private Integer productId;
    private Integer Quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productIds) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
