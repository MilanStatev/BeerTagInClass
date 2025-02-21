package com.example.beertag01inclass.models;

import com.example.beertag01inclass.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "beers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer extends BaseEntity {

    @Column(unique = true)
    @NotNull(message = "Name can't be empty.")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 symbols.")
    private String name;

    @Column
    private double price;

    @Column
    @Positive(message = "ABV should be positive.")
    private double abv;

    public Beer() {
    }

    public Beer(String name, double abv, double price) {
        this.name = name;
        this.abv = abv;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
