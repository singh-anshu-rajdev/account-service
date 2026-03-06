package com.smartBankElite.accountService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "country", nullable = false)
    private String country;

    // One-to-One relationship with Account (optional, for bidirectional)
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private AccountDetails accountDetails;

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
