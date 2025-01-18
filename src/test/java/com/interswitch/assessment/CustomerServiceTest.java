//package com.interswitch.assessment;
//
//
//import com.interswitch.assessment.dtos.CreateCustomerRequest;
//import com.interswitch.assessment.dtos.CreateCustomerResponse;
//import com.interswitch.assessment.model.Customer;
//import com.interswitch.assessment.repository.CustomerRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@Test
//        public void testCreateCustomer_Success() {
//
//            @Mock
//            private CustomerRepository customerRepository;
//            // Arrange
//            CreateCustomerRequest request = new CreateCustomerRequest("test@example.com", "John", "Doe", "1234567890", "123 Street");
//            when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//            when(customerRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
//            when(customerRepository.save(any(Customer.class))).thenReturn(new Customer(1L, ...));
//
//            // Act
//            CreateCustomerResponse response = customerService.createCustomer(request);
//
//            // Assert
//            assertNotNull(response.getId());
//            assertEquals("test@example.com", response.getEmail());
//        }
//
//    }
//
//}
