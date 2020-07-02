package br.com.pets.api.service.impl;

import br.com.pets.api.exceptionhandler.PetsException;
import br.com.pets.api.exceptionhandler.PetsNotUpdateException;
import br.com.pets.api.model.Breed;
import br.com.pets.api.model.Specie;
import br.com.pets.api.repository.SpecieRepository;
import br.com.pets.api.service.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecieServiceImpl implements SpecieService {

    @Autowired
    private SpecieRepository specieRepository;

    @Override
    public List<Specie> findAll() {
        return this.specieRepository.findAll();
    }

    @Override
    public List<Specie> findName(String name) {
        return this.specieRepository.findByName(name);
    }

    @Override
    public Specie create(Specie specie) {
        specie.setId(null);
        return this.specieRepository.save(specie);
    }

    @Override
    public Specie update(Specie specie) throws Exception {
        Optional<Specie> optionalSpecie = this.specieRepository.findById(specie.getId());
        if(!optionalSpecie.isPresent()) {
            throw new PetsNotUpdateException();
        }
        return this.specieRepository.save(specie);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Specie> optionalSpecie = this.specieRepository.findById(id);
        if(!optionalSpecie.isPresent()) {
            throw new PetsNotUpdateException();
        }
        this.specieRepository.delete(optionalSpecie.get());
    }
}
