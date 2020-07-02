package br.com.pets.api.service;

import br.com.pets.api.model.Specie;

import java.util.List;

public interface SpecieService {

    List<Specie> findAll();
    List<Specie> findName(String name);
    Specie create(Specie specie);
    Specie update(Specie specie) throws Exception;
    void delete(Long id) throws Exception;
}
