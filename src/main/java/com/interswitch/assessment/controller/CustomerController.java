package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.CustomerRequestDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.createCustomer(customerRequestDTO);
    }
}

