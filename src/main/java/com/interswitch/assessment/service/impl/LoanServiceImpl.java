package com.interswitch.assessment.service.impl;

package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.LoanRequestDTO;
import com.interswitch.assessment.dtos.LoanResponseDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.model.Loan;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.repository.CustomerRepository;
import com.interswitch.assessment.repository.LoanRepository;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public void repayLoans() {
        // 1. Find all unpaid loans
        List<Loan> loans = loanRepository.findByIsPaidOff(false);

        for (Loan loan : loans) {
            // 2. Calculate 10% of the loan amount
            BigDecimal repaymentAmount = loan.getAmount() * 0.1;

            // 3. Get the customer's savings account
            Customer customer = loan.getCustomer();
            SavingsAccount savingsAccount = savingsAccountRepository.findByCustomerId(customer.getId());

            if (savingsAccount != null && savingsAccount.getBalance().compareTo(repaymentAmount) >= 0) {
                savingsAccount.setBalance(savingsAccount.getBalance().subtract(repaymentAmount));
            }


            // 5. Update the loan repayment status
            loan.setAmount(loan.getAmount() - repaymentAmount);
            if (loan.getAmount() <= 0) {
                loan.setIsPaidOff(true);  // Mark loan as paid off if fully repaid
            }

            // Save the updated savings account and loan
            savingsAccountRepository.save(savingsAccount);
            loanRepository.save(loan);
        }
    }
}

@Override
public LoanResponseDTO requestLoan(LoanRequestDTO loanRequestDTO, Long customerId) {
    Optional<Customer> customerOpt = customerRepository.findById(customerId);
    if (!customerOpt.isPresent()) {
        throw new IllegalArgumentException("Customer not found.");
    }

    Customer customer = customerOpt.get();

    // Check if the customer already has a pending or unpaid loan
    List<Loan> loans = loanRepository.findByIsPaidOff(false);
    if (!loans.isEmpty()) {
        throw new IllegalStateException("Loan request denied. You have an unpaid loan.");
    }

    // Create new loan request
    Loan newLoan = new Loan();
    newLoan.setAmount(loanRequestDTO.getAmount());
    newLoan.setCustomer(customer);
    loanRepository.save(newLoan);

    return mapToLoanResponseDTO(newLoan);
}

@Override
public LoanResponseDTO approveLoan(Long loanId) {
    Optional<Loan> loanOpt = loanRepository.findById(loanId);
    if (!loanOpt.isPresent()) {
        throw new IllegalArgumentException("Loan not found.");
    }

    Loan loan = loanOpt.get();
    loan.setIsPaidOff(false);
    loanRepository.save(loan);

    return mapToLoanResponseDTO(loan);
}

@Override
public LoanResponseDTO rejectLoan(Long loanId) {
    Optional<Loan> loanOpt = loanRepository.findById(loanId);
    if (!loanOpt.isPresent()) {
        throw new IllegalArgumentException("Loan not found.");
    }

    Loan loan = loanOpt.get();
    loanRepository.delete(loan);

    return mapToLoanResponseDTO(loan);
}

private LoanResponseDTO mapToLoanResponseDTO(Loan loan) {
    LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
    loanResponseDTO.setLoanId(loan.getId());
    loanResponseDTO.setAmount(loan.getAmount());
    loanResponseDTO.setIsPaidOff(loan.isPaidOff());
    return loanResponseDTO;
}
}

