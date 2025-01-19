package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.CreditAccountRequest;
import com.interswitch.assessment.dtos.CreditAccountResponse;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.model.Transaction;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.repository.TransactionRepository;
import com.interswitch.assessment.service.AccountService;
import com.interswitch.assessment.utils.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final SavingsAccountRepository savingsAccountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public CreditAccountResponse debit(CreditAccountRequest request) {
        SavingsAccount savingsAccount = savingsAccountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (savingsAccount.getBalance() < request.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Update the account balance
        double newBalance = savingsAccount.getBalance() - request.getAmount();
        savingsAccount.setBalance(newBalance);
        savingsAccountRepository.save(savingsAccount);



        // Record the transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription(request.getDescription() != null ? request.getDescription() : "Account debited");
        transactionRepository.save(transaction);

        return new CreditAccountResponse(request.getAccountNumber(), newBalance);
    }

    @Override
    public CreditAccountResponse credit(CreditAccountRequest request) {
        SavingsAccount savingsAccount = savingsAccountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        // Update the account balance
        double newBalance = savingsAccount.getBalance() + request.getAmount();
        savingsAccount.setBalance(newBalance);
        savingsAccountRepository.save(savingsAccount);


        // Record the transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription(request.getDescription() != null ? request.getDescription() : "Account credited");
        transactionRepository.save(transaction);

        return new CreditAccountResponse(request.getAccountNumber(), newBalance);
    }
}
