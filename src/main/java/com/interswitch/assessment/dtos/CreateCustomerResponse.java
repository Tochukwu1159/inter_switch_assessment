package com.interswitch.assessment.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerResponse {
    private Long id;
    private String email;
    private String fullName;
}

