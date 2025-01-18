package com.interswitch.assessment.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovalRequest {

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotNull(message = "Approval status is required")
    private Boolean approve; // true for approval, false for rejection

    private String rejectReason;

}

