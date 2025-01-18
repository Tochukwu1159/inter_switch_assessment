package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.CreditAccountRequest;
import com.interswitch.assessment.dtos.CreditAccountResponse;

public interface AccountService {
    CreditAccountResponse debit(CreditAccountRequest request);
    CreditAccountResponse credit(CreditAccountRequest request);
}