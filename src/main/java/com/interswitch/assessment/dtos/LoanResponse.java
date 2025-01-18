package com.interswitch.assessment.dtos;

import com.interswitch.assessment.utils.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoanResponse {

    private Long loanId;

    private String accountNumber;

    private Double amount;

    private LoanStatus status;

    private Double outstandingBalance;

    private LocalDateTime requestDate;

    private LocalDateTime approvalDate;

    // Constructor, Getters, and Setters
}

