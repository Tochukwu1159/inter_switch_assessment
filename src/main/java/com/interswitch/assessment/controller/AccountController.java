package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.CreditAccountRequest;
import com.interswitch.assessment.dtos.CreditAccountResponse;
import com.interswitch.assessment.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/credit")
    public ResponseEntity<CreditAccountResponse> creditAccount(
            @Valid @RequestBody CreditAccountRequest request) {
        CreditAccountResponse response = accountService.credit( request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/debit")
    public ResponseEntity<CreditAccountResponse> debitAccount(
            @Valid @RequestBody CreditAccountRequest request) {

        CreditAccountResponse response = accountService.debit(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
