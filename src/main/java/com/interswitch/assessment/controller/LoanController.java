package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.*;
import com.interswitch.assessment.model.Loan;
import com.interswitch.assessment.service.LoanService;
import com.interswitch.assessment.utils.LoanRepaymentJob;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/request")
    public ResponseEntity<LoanResponse> requestLoan(@Valid @RequestBody LoanRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.requestLoan(request));
    }

    @PostMapping("/approve")
    public ResponseEntity<LoanResponse> approveOrRejectLoan(@Valid @RequestBody LoanApprovalRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.approveOrRejectLoan(request));
    }

}