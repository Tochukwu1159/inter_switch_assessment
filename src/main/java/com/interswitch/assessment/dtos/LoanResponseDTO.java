package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanResponseDTO {

    private Long loanId;
    private Double amount;
    private boolean isPaidOff;
}

