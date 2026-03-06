package com.smartBankElite.accountService.Utlis;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.UUID;

@Slf4j
public class CredentialGenerator {

    private static final String PASSWORD_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$!";

    private static final String USERNAME_CHARS = "0123456789@#$!";
    private static final SecureRandom random = new SecureRandom();

    // Generate username
    public static String generateUsername(String firstName) {

        String base = firstName.toLowerCase();
        StringBuilder suffix = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            suffix.append(USERNAME_CHARS.charAt(random.nextInt(USERNAME_CHARS.length())));
        }

        return base + suffix;
    }

    // Generate password
    public static String generatePassword() {

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append(PASSWORD_CHARS.charAt(random.nextInt(PASSWORD_CHARS.length())));
        }
        return password.toString();
    }

}