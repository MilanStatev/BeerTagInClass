package com.example.beertag01inclass.controllers;

import com.example.beertag01inclass.exceptions.DuplicateEntityException;
import com.example.beertag01inclass.exceptions.EntityNotFoundException;
import com.example.beertag01inclass.models.Beer;
import com.example.beertag01inclass.services.BeerService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
        try {
            beerService.addBeer(beer);
            return beer;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        try{
            beerService.deleteBeerById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer updateBeer(@Valid @PathVariable("id") long id,@Valid @RequestBody Beer beer){
        try {
            return beerService.updateBeer(id, beer);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}

