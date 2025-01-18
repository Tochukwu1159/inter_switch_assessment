package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.CreateCustomerRequest;
import com.interswitch.assessment.dtos.CreateCustomerResponse;
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
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use.");
        }

        if (customerRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("Phone number already in use.");
        }

        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        Customer savedCustomer = customerRepository.save(customer);

        return new CreateCustomerResponse(savedCustomer.getId(),
                savedCustomer.getEmail(),
                savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
    }
}
