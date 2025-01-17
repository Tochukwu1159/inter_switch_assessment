package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.LoanRequestDTO;
import com.interswitch.assessment.dtos.LoanResponseDTO;

public interface LoanService {
    void repayLoans();
    LoanResponseDTO rejectLoan(Long loanId);
    LoanResponseDTO requestLoan(LoanRequestDTO loanRequestDTO, Long customerId);
    LoanResponseDTO approveLoan(Long loanId);
}
