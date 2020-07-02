package br.com.pets.api.service.impl;

import br.com.pets.api.exceptionhandler.PetsException;
import br.com.pets.api.exceptionhandler.PetsNotUpdateException;
import br.com.pets.api.model.Breed;
import br.com.pets.api.repository.BreedRepository;
import br.com.pets.api.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository breedRepository;

    @Override
    public List<Breed> findAll() {
        return this.breedRepository.findAll();
    }

    @Override
    public List<Breed> findName(String name) {
        return this.breedRepository.findByName(name);
    }

    @Override
    public Breed create(Breed breed) {
        breed.setId(null);
        return this.breedRepository.save(breed);
    }

    @Override
    public Breed update(Breed breed) throws Exception {
        Optional<Breed> optionalBreed = this.breedRepository.findById(breed.getId());
        if(!optionalBreed.isPresent()) {
            throw new PetsNotUpdateException();
        }
        return this.breedRepository.save(breed);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Breed> optionalBreed = this.breedRepository.findById(id);

        if(!optionalBreed.isPresent()) {
            throw new PetsException();
        }
        this.breedRepository.delete(optionalBreed.get());
    }
}
