package com.infoworld.jpa.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductPriceId implements Serializable {
    private String region;
    private Integer productId;

    public ProductPriceId() {
    }

    public ProductPriceId(String region, Integer productId) {
        this.region = region;
        this.productId = productId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductPriceId{" +
                "region='" + region + '\'' +
                ", productId=" + productId +
                '}';
    }
}
