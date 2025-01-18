package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.CreateCustomerRequest;
import com.interswitch.assessment.dtos.CreateCustomerResponse;


public interface CustomerService {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request);
}