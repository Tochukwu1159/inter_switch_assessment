package com.interswitch.assessment;

import com.interswitch.assessment.dtos.CreateSavingsAccountRequest;
import com.interswitch.assessment.dtos.CreateSavingsAccountResponse;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.repository.CustomerRepository;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.service.impl.SavingsAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SavingsAccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SavingsAccountRepository savingsAccountRepository;

    @InjectMocks
    private SavingsAccountServiceImpl savingsAccountService;

    @Test
    public void testCreateSavingsAccount_Success() {
        CreateSavingsAccountRequest request = new CreateSavingsAccountRequest(1L);
        Customer customer = new Customer(1L, "John", "Doe", "johndoe@example.com", "1234567890", "7 asajon way");

        SavingsAccount savingsAccount = SavingsAccount.builder()
                .id(1L)
                .accountNumber("ACC123456789")
                .customerId(customer.getId())
                .balance(0.0)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(savingsAccountRepository.findByCustomerId(anyLong())).thenReturn(Optional.empty());
        when(savingsAccountRepository.save(any(SavingsAccount.class))).thenReturn(savingsAccount);

        // Act
        CreateSavingsAccountResponse response = savingsAccountService.createSavingsAccount(request);

        // Assert
        assertNotNull(response.getAccountNumber(), "Account number should not be null");
        assertEquals(0.0, response.getBalance(), "Initial balance should be 0.0");
        assertEquals(savingsAccount.getAccountNumber(), response.getAccountNumber(), "Account numbers should match");
    }

    @Test
    public void testCreateSavingsAccount_Failure_CustomerNotFound() {
        // Arrange
        CreateSavingsAccountRequest request = new CreateSavingsAccountRequest(1L);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccountService.createSavingsAccount(request);
        });

        assertEquals("Customer not found", exception.getMessage(), "Exception message should match");
    }

    @Test
    public void testCreateSavingsAccount_Failure_CustomerAlreadyHasAccount() {
        // Arrange
        CreateSavingsAccountRequest request = new CreateSavingsAccountRequest(1L);
        Customer customer = new Customer(1L, "John", "Doe", "johndoe@example.com", "1234567890", "7 asajon way");

        SavingsAccount existingAccount = SavingsAccount.builder()
                .id(1L)
                .accountNumber("ACC123456789")
                .customerId(customer.getId())
                .balance(0.0)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(savingsAccountRepository.findByCustomerId(anyLong())).thenReturn(Optional.of(existingAccount));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccountService.createSavingsAccount(request);
        });

        assertEquals("Customer already has a savings account", exception.getMessage(), "Exception message should match");
    }
}
