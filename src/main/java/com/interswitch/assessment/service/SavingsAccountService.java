package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.CreateSavingsAccountRequest;
import com.interswitch.assessment.dtos.CreateSavingsAccountResponse;
import com.interswitch.assessment.dtos.SavingsAccountRequestDTO;
import com.interswitch.assessment.model.SavingsAccount;

public interface SavingsAccountService {
    CreateSavingsAccountResponse createSavingsAccount(CreateSavingsAccountRequest request);}
