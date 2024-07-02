package com.example.beertag01inclass.controllers;

import com.example.beertag01inclass.models.Beer;
import com.example.beertag01inclass.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public List<Beer> getAll() {
        return beerService.getAllBeers();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable("id") long id) {
        try {
            return beerService.getBeerById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Beer with id %d not found.", id));
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
            beerService.addBeer(beer);
            return beer;
    }

    @DeleteMapping("/{id}")
    public Beer deleteById(@PathVariable("id") long id) {
        return beerService.deleteBeerById(id);
    }

//    @PutMapping
//    public Beer updateByName(@PathVariable("id") long id, )
}

