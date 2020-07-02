package br.com.pets.api.service.impl;

import br.com.pets.api.exceptionhandler.PetsNotUpdateException;
import br.com.pets.api.model.Pet;
import br.com.pets.api.repository.PetRepository;
import br.com.pets.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> findAll() {
        return this.petRepository.findAll();
    }

    @Override
    public List<Pet> findName(String name) {
        return this.petRepository.findByName(name);
    }

    @Override
    public List<Pet> findIdBreed(Long idBreed) {
        return this.petRepository.findByIdBreed(idBreed);
    }

    @Override
    public List<Pet> findIdSpecie(Long idSpecie) {
        return this.petRepository.findByIdSpecie(idSpecie);
    }

    @Override
    public Pet create(Pet pet) {
        pet.setId(null);
        return this.petRepository.save(pet);
    }

    @Override
    public Pet update(Pet pet) throws Exception {
        Optional<Pet> optionalpet = this.petRepository.findById(pet.getId());
        if(!optionalpet.isPresent()) {
            throw new PetsNotUpdateException();
        }
        return this.petRepository.save(pet);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Pet> optionalpet = this.petRepository.findById(id);
        if(!optionalpet.isPresent()) {
            throw new PetsNotUpdateException();
        }
        this.petRepository.delete(optionalpet.get());
    }
}
