package com.example.beertag01inclass.services;

import com.example.beertag01inclass.models.Beer;
import com.example.beertag01inclass.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    @Override
    public void addBeer(Beer beer) {
        beerRepository.saveAndFlush(beer);
    }

    @Override
    public Beer getBeerById(long id) {
        return beerRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Beer deleteBeerById(long id) {
        Beer beer = beerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Beer with id %d not found.", id))
                );
        beerRepository.deleteById(id);
        return beer;
    }
}
