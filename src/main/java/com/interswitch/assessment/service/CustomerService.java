package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.CustomerRequestDTO;
import com.interswitch.assessment.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(CustomerRequestDTO customerRequestDTO);
    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomerById(Long customerId);
}