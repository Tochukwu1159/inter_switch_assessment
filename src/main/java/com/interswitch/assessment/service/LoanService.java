package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.*;

public interface LoanService {
    LoanResponse requestLoan(LoanRequest request);
    LoanResponse approveOrRejectLoan(LoanApprovalRequest request);
}
