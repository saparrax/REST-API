package com.mca.app.service.dto;

public class Size {

    private Long id;
    private Long productId;
    private Boolean backSoon;
    private Boolean special;

    public Size(Long id, Long productId, Boolean backSoon, Boolean special) {
        this.id = id;
        this.productId = productId;
        this.backSoon = backSoon;
        this.special = special;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getBackSoon() {
        return backSoon;
    }

    public void setBackSoon(Boolean backSoon) {
        this.backSoon = backSoon;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }
}
