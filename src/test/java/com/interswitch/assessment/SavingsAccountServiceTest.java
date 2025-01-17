package com.interswitch.assessment;

import com.interswitch.assessment.dtos.SavingsAccountRequestDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.service.CustomerService;
import com.interswitch.assessment.service.impl.SavingsAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SavingsAccountServiceTest {

    @Mock
    private SavingsAccountRepository savingsAccountRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private SavingsAccountServiceImpl savingsAccountService;

    private SavingsAccountRequestDTO savingsAccountRequestDTO;

    @BeforeEach
    public void setup() {
        savingsAccountRequestDTO = new SavingsAccountRequestDTO();
        savingsAccountRequestDTO.setInitialDeposit(BigDecimal.valueOf(1000.0));
    }

    @Test
    void testCreateSavingsAccount() {
        Customer customer = new Customer();
        when(customerService.getCustomerByEmail(String.valueOf(anyLong()))).thenReturn(Optional.of(customer));
        when(savingsAccountRepository.save(any(SavingsAccount.class))).thenReturn(new SavingsAccount());

        SavingsAccount account = savingsAccountService.createSavingsAccount(1L, savingsAccountRequestDTO);

        assertNotNull(account);
        verify(savingsAccountRepository, times(1)).save(any(SavingsAccount.class));
    }
}

