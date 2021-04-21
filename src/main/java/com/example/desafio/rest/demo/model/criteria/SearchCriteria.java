package com.example.desafio.rest.demo.model.criteria;

import lombok.Getter;

@Getter
public class SearchCriteria {
    private String name;
    private Double min_price;
    private Double max_price;

    public String getName() {
        return this.name;
    }

    public Double getMinPrice() {
        return this.min_price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinPrice(Double min_price) {
        this.min_price = min_price;
    }

    public void setMaxPrice(Double max_price) {
        this.max_price = max_price;
    }

    public Double getMaxPrice() {
        return this.max_price;
    }
}
