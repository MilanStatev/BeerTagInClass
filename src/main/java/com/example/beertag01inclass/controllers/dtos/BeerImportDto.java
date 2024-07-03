package com.example.beertag01inclass.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerImportDto {
    @NotNull(message = "Name can't be empty.")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 symbols.")
    private String name;

    @Positive(message = "ABV should be positive.")
    private double abv;

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
}
