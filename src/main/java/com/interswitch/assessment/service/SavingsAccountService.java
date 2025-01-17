package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.SavingsAccountRequestDTO;
import com.interswitch.assessment.model.SavingsAccount;

public interface SavingsAccountService {
    SavingsAccount createSavingsAccount(Long customerId, SavingsAccountRequestDTO savingsAccountRequestDTO);
}
