package br.com.pets.api.service;

import br.com.pets.api.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findEmail(String email);
    Customer create(Customer customer);
    Customer update(Customer customer) throws Exception;
    void delete(Long id) throws Exception;

}
