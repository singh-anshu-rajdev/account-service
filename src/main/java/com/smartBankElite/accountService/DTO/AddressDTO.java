package com.smartBankElite.accountService.DTO;

import lombok.Data;

@Data
public class AddressDTO {

    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
