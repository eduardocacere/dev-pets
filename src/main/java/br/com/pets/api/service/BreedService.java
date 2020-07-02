package br.com.pets.api.service;

import br.com.pets.api.model.Breed;

import java.util.List;

public interface BreedService {

    List<Breed> findAll();
    List<Breed> findName(String name);
    Breed create(Breed breed);
    Breed update(Breed breed) throws Exception;
    void delete(Long id) throws Exception;

}
