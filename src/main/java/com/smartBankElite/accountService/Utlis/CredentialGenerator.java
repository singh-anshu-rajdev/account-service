package com.smartBankElite.accountService.Utlis;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.UUID;

@Slf4j
public class CredentialGenerator {

    private static final SecureRandom random = new SecureRandom();

    // Generate username
    public static String generateUsername(String firstName) {

        String base = firstName.toLowerCase();
        StringBuilder suffix = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            suffix.append(SmartBankEliteConstants.USERNAME_CHARS.toString()
                    .charAt(random.nextInt(SmartBankEliteConstants.USERNAME_CHARS.toString().length())));
        }

        return base + suffix;
    }

    // Generate password
    public static String generatePassword() {

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append(SmartBankEliteConstants.PASSWORD_CHARS.toString()
                    .charAt(random.nextInt(SmartBankEliteConstants.PASSWORD_CHARS.toString().length())));
        }
        log.info("Generated password: {}", password.toString());
        return password.toString();
    }

}