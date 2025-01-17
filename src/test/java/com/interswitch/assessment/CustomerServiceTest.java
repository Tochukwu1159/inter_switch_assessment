package com.interswitch.assessment;

import com.interswitch.assessment.dtos.CustomerRequestDTO;
import com.interswitch.assessment.model.Customer;
import com.interswitch.assessment.repository.CustomerRepository;
import com.interswitch.assessment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerRequestDTO customerRequestDTO;

    @BeforeEach
    public void setup() {
        customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("John Doe");
        customerRequestDTO.setEmail("john@example.com");
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());

        Customer customer = customerService.createCustomer(customerRequestDTO);

        assertNotNull(customer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
