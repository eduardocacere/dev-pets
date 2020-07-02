package br.com.pets.api.repository;

import br.com.pets.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByName(String name);
    List<Pet> findByIdBreed(Long idBreed);
    List<Pet> findByIdSpecie(Long idSpecie);
}
