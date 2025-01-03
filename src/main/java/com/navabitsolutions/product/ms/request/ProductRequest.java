package com.navabitsolutions.product.ms.request;

import java.math.BigDecimal;

public class ProductRequest {
    private String title;
    private BigDecimal price;
    private Integer quality;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
