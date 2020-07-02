package br.com.pets.api.service.impl;

import br.com.pets.api.exceptionhandler.PetsNotUpdateException;
import br.com.pets.api.model.Customer;
import br.com.pets.api.repository.CustomerRepository;
import br.com.pets.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findEmail(String email) {
        return this.customerRepository.findByEmail(email);
    }

    @Override
    public Customer create(Customer customer) {
        customer.setId(null);
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) throws Exception {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(customer.getId());
        if(!optionalCustomer.isPresent()) {
            throw new PetsNotUpdateException();
        }
        return this.customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if(!optionalCustomer.isPresent()) {
            throw new PetsNotUpdateException();
        }
        this.customerRepository.delete(optionalCustomer.get());
    }
}
