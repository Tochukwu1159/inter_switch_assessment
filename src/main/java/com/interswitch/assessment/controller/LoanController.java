package com.interswitch.assessment.controller;

import com.interswitch.assessment.dtos.LoanRequestDTO;
import com.interswitch.assessment.dtos.LoanResponseDTO;
import com.interswitch.assessment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/request")
    public LoanResponseDTO requestLoan(@RequestBody LoanRequestDTO loanRequestDTO, @RequestParam Long customerId) {
        return loanService.requestLoan(loanRequestDTO, customerId);
    }

    @PostMapping("/{loanId}/approve")
    public LoanResponseDTO approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }

    @PostMapping("/{loanId}/reject")
    public LoanResponseDTO rejectLoan(@PathVariable Long loanId) {
        return loanService.rejectLoan(loanId);
    }
}
