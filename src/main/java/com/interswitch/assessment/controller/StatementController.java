package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.StatementRequest;
import com.interswitch.assessment.dtos.StatementResponse;
import com.interswitch.assessment.service.StatementService;
import com.interswitch.assessment.utils.LoanRepaymentJob;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statements")
public class StatementController {

    private final StatementService statementService;


    @GetMapping
    public ResponseEntity<StatementResponse> getAccountStatement(@Valid @RequestBody StatementRequest request) {
        StatementResponse response = statementService.getAccountStatement(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}

