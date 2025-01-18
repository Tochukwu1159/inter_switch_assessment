package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.CreateSavingsAccountRequest;
import com.interswitch.assessment.dtos.CreateSavingsAccountResponse;
import com.interswitch.assessment.service.SavingsAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class SavingsAccountController {

    @Autowired
    private SavingsAccountService savingsAccountService;


    @PostMapping
    public ResponseEntity<CreateSavingsAccountResponse> createSavingsAccount(@Valid @RequestBody CreateSavingsAccountRequest request) {
        CreateSavingsAccountResponse response = savingsAccountService.createSavingsAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

