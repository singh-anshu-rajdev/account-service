package com.smartBankElite.accountService.Utlis;

import lombok.Getter;

@Getter
public enum SmartBankEliteConstants {
    USER_ID("userId"),
    NAME("Name"),
    USERNAME("username"),
    EMAIL_ID("emailId"),
    CREATED_AT("created At"),
    PASSWORD_CHARS("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$!"),
    USERNAME_CHARS("0123456789@#$!"),
    AUTHORIZATION("authorization");

    private final String value;

    SmartBankEliteConstants(String value) {
        this.value = value;
    }
}
