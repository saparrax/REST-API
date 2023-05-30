package com.mca.app.service.dto;

import java.io.Serializable;

public class Product implements Serializable {
    private Long id;
    private Integer sequence;

    public Product(Long id, Integer sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
