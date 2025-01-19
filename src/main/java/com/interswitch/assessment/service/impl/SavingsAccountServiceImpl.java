package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.CreateSavingsAccountRequest;
import com.interswitch.assessment.dtos.CreateSavingsAccountResponse;
import com.interswitch.assessment.dtos.SavingsAccountRequestDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.repository.CustomerRepository;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.service.CustomerService;
import com.interswitch.assessment.service.SavingsAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SavingsAccountServiceImpl implements SavingsAccountService {


    private final SavingsAccountRepository savingsAccountRepository;


    private final CustomerRepository customerRepository;

    @Override
    public CreateSavingsAccountResponse createSavingsAccount(CreateSavingsAccountRequest request) {
        customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (savingsAccountRepository.findByCustomerId(request.getCustomerId()).isPresent()) {
            throw new IllegalArgumentException("Customer already has a savings account");
        }

        // Create account
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setCustomerId(request.getCustomerId());
        savingsAccount.setAccountNumber(generateAccountNumber());
        savingsAccount.setBalance(0.0);

        SavingsAccount savedAccount = savingsAccountRepository.save(savingsAccount);

        return new CreateSavingsAccountResponse(savedAccount.getAccountNumber(), savedAccount.getBalance());
    }

    private String generateAccountNumber() {
        int year = LocalDate.now().getYear();
        int randomNumbers = (int) (Math.random() * 1_000_000); // Generate a random number between 0 and 999999
        return String.format("%d%06d", year, randomNumbers);
    }
}

