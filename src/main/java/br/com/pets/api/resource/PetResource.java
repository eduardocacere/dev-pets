package br.com.pets.api.resource;

import br.com.pets.api.model.Pet;
import br.com.pets.api.service.PetService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
@Api(value="pet", description="Operations pertaining to Pet")
public class PetResource {

    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> findByAll() {
        List<Pet> list = this.petService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pet>> findByName(@PathVariable String name) {
        List<Pet> list = this.petService.findName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/breed/{idBreed}")
    public ResponseEntity<List<Pet>> findByIdBreed(@PathVariable Long idBreed) {
        List<Pet> list = this.petService.findIdBreed(idBreed);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/specie/{idSpecie}")
    public ResponseEntity<List<Pet>> findBySpecie(@PathVariable Long idSpecie) {
        List<Pet> list = this.petService.findIdSpecie(idSpecie);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Pet> save(@RequestBody @Valid Pet Pet) {
        Pet PetNew = petService.create(Pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(PetNew);
    }

    @PutMapping
    public ResponseEntity<Pet> update(@RequestBody @Valid Pet Pet) throws Exception {
        Pet PetNew = petService.update(Pet);
        return ResponseEntity.ok(PetNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> delete(@PathVariable Long id) throws Exception {
        petService.delete(id);
        return ResponseEntity.ok().build();
    }
}
