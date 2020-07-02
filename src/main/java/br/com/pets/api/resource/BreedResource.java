package br.com.pets.api.resource;

import br.com.pets.api.model.Breed;
import br.com.pets.api.service.BreedService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/breed")
@Api(value="breed", description="Operations pertaining to Breed")
public class BreedResource {

    @Autowired
    private BreedService breedService;

    @GetMapping("/all")
    public ResponseEntity<List<Breed>> findByAll() {
        List<Breed> list = this.breedService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Breed>> findByName(@PathVariable String name) {
        List<Breed> list = this.breedService.findName(name);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Breed> save(@RequestBody @Valid Breed breed) {

        Breed breedNew = breedService.create(breed);
        return ResponseEntity.status(HttpStatus.CREATED).body(breedNew);
    }

    @PutMapping
    public ResponseEntity<Breed> update(@RequestBody @Valid Breed breed) throws Exception {

        Breed breedNew = breedService.update(breed);
        return ResponseEntity.ok(breedNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Breed> delete(@PathVariable Long id) throws Exception {
        breedService.delete(id);
        return ResponseEntity.ok().build();
    }
}
