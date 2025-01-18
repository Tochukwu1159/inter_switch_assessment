package com.interswitch.assessment.repository;

import com.interswitch.assessment.model.SavingsAccount;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    Optional<SavingsAccount> findByCustomerId(Long customerId);

    Optional<SavingsAccount> findByAccountNumber(@NotNull String accountNumber);
}
