package com.interswitch.assessment.repository;

import com.interswitch.assessment.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    SavingsAccount findByCustomerId(Long customerId);
}
