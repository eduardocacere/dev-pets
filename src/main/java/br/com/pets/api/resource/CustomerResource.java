package br.com.pets.api.resource;

import br.com.pets.api.model.Customer;
import br.com.pets.api.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Api(value="Customer", description="Operations pertaining to Customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findByAll() {
        List<Customer> list = this.customerService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/email")
    public ResponseEntity<Customer> findByName(@RequestParam String email) {
        Customer customer = this.customerService.findEmail(email);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody @Valid Customer Customer) {

        Customer CustomerNew = customerService.create(Customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerNew);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody @Valid Customer Customer) throws Exception {

        Customer CustomerNew = customerService.update(Customer);
        return ResponseEntity.ok(CustomerNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id) throws Exception {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
