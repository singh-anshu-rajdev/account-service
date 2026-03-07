package com.smartBankElite.accountService.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAccountRequestDTO {

    // Personal Information
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;

    // Contact Information
    private String email;
    private String mobileNumber;

    // Address
    private AddressDTO addressDTO;

    // Account Details
    private String uniqueId;
    private String accountType;
    private Double initialDeposit;

    // Consent
    private Boolean termsAccepted;
    private Boolean marketingConsent;
}
