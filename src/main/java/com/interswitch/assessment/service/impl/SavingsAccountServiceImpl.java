package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.SavingsAccountRequestDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.service.CustomerService;
import com.interswitch.assessment.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public SavingsAccount createSavingsAccount(Long customerId, SavingsAccountRequestDTO savingsAccountRequestDTO) {
        Customer customer = customerService.getCustomerById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        SavingsAccount account = new SavingsAccount();
        account.setBalance(savingsAccountRequestDTO.getInitialDeposit());
        account.setCustomer(customer);

        return savingsAccountRepository.save(account);
    }
}

