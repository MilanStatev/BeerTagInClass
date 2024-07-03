package com.example.beertag01inclass.services.impl;

import com.example.beertag01inclass.exceptions.DuplicateEntityException;
import com.example.beertag01inclass.exceptions.EntityNotFoundException;
import com.example.beertag01inclass.models.Beer;
import com.example.beertag01inclass.repositories.BeerRepository;
import com.example.beertag01inclass.services.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private ObjectMapper mapper;


    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    @Override
    public void createBeer(Beer beer) {
        Optional<Beer> current = beerRepository.findByName(beer.getName());

        if (current.isPresent()) {
            throw new DuplicateEntityException(String.format("Beer %s already exist", beer.getName()));
        }

        beerRepository.saveAndFlush(beer);
    }

    @Override
    public Beer getBeerById(long id) {
        return findBeerById(id);
    }

    @Override
    public void deleteBeerById(long id) {
        findBeerById(id);
        beerRepository.deleteById(id);
    }

    @Override
    public Beer updateBeer(Beer beer) {
        Beer beerToUpdate = findBeerById(beer.getId());

        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());

        beerRepository.saveAndFlush(beerToUpdate);

        return beerToUpdate;
    }

    private Beer findBeerById(long id) {
        return beerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Beer with id %d not found.", id))
                );
    }
}
