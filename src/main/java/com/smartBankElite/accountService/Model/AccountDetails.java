package com.smartBankElite.accountService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "account_details")
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    // Personal Information
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "nationality")
    private String nationality;

    // Contact Information
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    // Account Details
    @Column(name = "unique_id", nullable = false)
    private String uniqueId;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "initial_deposit", nullable = false)
    private Double initialDeposit;

    // Consent
    @Column(name = "terms_accepted", nullable = false)
    private Boolean termsAccepted;

    @Column(name = "marketing_consent")
    private Boolean marketingConsent;

    // One-to-One relationship with Address
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Audit Fields
    @Column(name = "deleted_flag")
    private Boolean deletedFlag = false;

    @Column(name = "created_by")
    private String createdBy="system";

    @Column(name = "created_at")
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name = "updated_by")
    private String updatedBy="system";

    @Column(name = "updated_at")
    private LocalDateTime updatedAt=LocalDateTime.now();
}
