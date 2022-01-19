package com.nik.code.ecom.dto.userProducts;

import java.util.Objects;

public class UserProductDTO {
    private Integer productId;
    private Integer quantity=1;

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = Objects.nonNull(quantity) ? quantity : 1;
    }
}
