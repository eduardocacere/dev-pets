package br.com.pets.api.service;

import br.com.pets.api.model.Pet;

import java.util.List;

public interface PetService {

    List<Pet> findAll();
    List<Pet> findName(String name);
    List<Pet> findIdBreed(Long idBreed);
    List<Pet> findIdSpecie(Long idSpecie);
    Pet create(Pet pet);
    Pet update(Pet pet) throws Exception;
    void delete(Long id) throws Exception;

}
