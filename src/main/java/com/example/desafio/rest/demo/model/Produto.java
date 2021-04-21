package com.example.desafio.rest.demo.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@SuppressWarnings("ALL")
@Entity
@DynamicUpdate

public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private @Valid @NotNull String name;

    @Column(name = "description")
    private @Valid @NotNull String description;

    @Column(name = "price")
    private @Valid @Positive Double price;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return this.id; }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setName(String q) {
        this.name = q;
    }
}
