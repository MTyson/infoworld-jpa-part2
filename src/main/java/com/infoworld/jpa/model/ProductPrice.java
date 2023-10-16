package com.infoworld.jpa.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT_PRICE")
public class ProductPrice {

    @EmbeddedId
    private ProductPriceId id;
    private Double price;

    public ProductPrice() {
    }

    public ProductPrice(ProductPriceId id, Double price) {
        this.id = id;
        this.price = price;
    }

    public ProductPriceId getId() {
        return id;
    }

    public void setId(ProductPriceId id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
