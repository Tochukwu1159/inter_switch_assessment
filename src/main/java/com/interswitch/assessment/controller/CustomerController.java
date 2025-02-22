package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.CreateCustomerRequest;
import com.interswitch.assessment.dtos.CreateCustomerResponse;
import com.interswitch.assessment.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        CreateCustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

