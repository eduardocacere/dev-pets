package br.com.pets.api.repository;

import br.com.pets.api.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {

    List<Breed> findByName(String name);
}
