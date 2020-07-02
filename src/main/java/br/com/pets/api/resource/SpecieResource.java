package br.com.pets.api.resource;

import br.com.pets.api.model.Specie;
import br.com.pets.api.service.SpecieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/specie")
@Api(value="specie", description="Operations pertaining to Specie")
public class SpecieResource {


    @Autowired
    private SpecieService specieService;

    @GetMapping("/all")
    public ResponseEntity<List<Specie>> findByAll() {
        List<Specie> list = this.specieService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Specie>> findByName(@PathVariable String name) {
        List<Specie> list = this.specieService.findName(name);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Specie> save(@RequestBody @Valid Specie Specie) {

        Specie SpecieNew = specieService.create(Specie);
        return ResponseEntity.status(HttpStatus.CREATED).body(SpecieNew);
    }

    @PutMapping
    public ResponseEntity<Specie> update(@RequestBody @Valid Specie Specie) throws Exception {

        Specie SpecieNew = specieService.update(Specie);
        return ResponseEntity.ok(SpecieNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Specie> delete(@PathVariable Long id) throws Exception {
        specieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
