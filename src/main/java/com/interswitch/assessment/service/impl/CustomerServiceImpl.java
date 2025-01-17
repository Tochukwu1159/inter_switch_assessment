package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.CustomerRequestDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.repository.CustomerRepository;
import com.interswitch.assessment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer();
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return Optional.ofNullable(customerRepository.findByEmail(email));
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }
}

