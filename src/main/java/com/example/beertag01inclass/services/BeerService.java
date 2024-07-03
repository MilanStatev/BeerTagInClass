package com.example.beertag01inclass.services;

import com.example.beertag01inclass.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers();

    void createBeer(Beer beer);

    Beer getBeerById(long id);

    void deleteBeerById(long id);

    Beer updateBeer(Beer beer);
}
