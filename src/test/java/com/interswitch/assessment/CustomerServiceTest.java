package com.interswitch.assessment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.interswitch.assessment.dtos.CreateCustomerRequest;
import com.interswitch.assessment.dtos.CreateCustomerResponse;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.repository.CustomerRepository;
import com.interswitch.assessment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer_Success() {
        // Arrange
        CreateCustomerRequest request = new CreateCustomerRequest(
                "test@example.com",
                "John",
                "Doe",
                "1234567890",
                "123 Street"
        );

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            customer.setId(1L); // Simulate database assigning an ID
            return customer;
        });

        // Act
        CreateCustomerResponse response = customerService.createCustomer(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("John Doe", response.getFullName());
    }

    @Test
    public void testCreateCustomer_Failure_EmailAlreadyInUse() {
        // Arrange
        CreateCustomerRequest request = new CreateCustomerRequest(
                "test@example.com",
                "John",
                "Doe",
                "1234567890",
                "123 Street"
        );

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(new Customer()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> customerService.createCustomer(request));

        assertEquals("Email already in use.", exception.getMessage());
    }

    @Test
    public void testCreateCustomer_Failure_PhoneNumberAlreadyInUse() {
        // Arrange
        CreateCustomerRequest request = new CreateCustomerRequest(
                "test@example.com",
                "John",
                "Doe",
                "1234567890",
                "123 Street"
        );

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(new Customer()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> customerService.createCustomer(request));

        assertEquals("Phone number already in use.", exception.getMessage());
    }
}
