package com.smartBankElite.accountService.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAccountResponseDTO {

    // Personal Information
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;

    // Contact Information
    private String email;
    private String mobileNumber;

    // Account Details
    private String accountType;
}
