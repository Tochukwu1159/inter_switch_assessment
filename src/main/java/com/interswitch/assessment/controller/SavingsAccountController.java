package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.SavingsAccountRequestDTO;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class SavingsAccountController {

    @Autowired
    private SavingsAccountService savingsAccountService;

    @PostMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount createSavingsAccount(@PathVariable Long customerId,
                                               @RequestBody SavingsAccountRequestDTO savingsAccountRequestDTO) {
        return savingsAccountService.createSavingsAccount(customerId, savingsAccountRequestDTO);
    }
}

