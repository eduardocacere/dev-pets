package br.com.pets.api.repository;

import br.com.pets.api.model.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecieRepository extends JpaRepository<Specie, Long> {

    List<Specie> findByName(String name);
}
