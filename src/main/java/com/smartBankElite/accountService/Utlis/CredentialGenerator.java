package com.smartBankElite.accountService.Utlis;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class CredentialGenerator {

    private static final SecureRandom random = new SecureRandom();

    // Generate username
    public static String generateUsername(String firstName) {

        String base = firstName.toLowerCase();
        String numbers = SmartBankEliteConstants.NUMBERS.getValue();
        String letters = SmartBankEliteConstants.LOWER_CASE.getValue();
        StringBuilder username = new StringBuilder(base);

        // add 3 numbers
        for (int i = 0; i < 3; i++) {
            username.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        // add 2 letters
        for (int i = 0; i < 2; i++) {
            username.append(letters.charAt(random.nextInt(letters.length())));
        }
        return username.toString();
    }

    // Generate password
    public static String generatePassword() {

        String upper = SmartBankEliteConstants.UPPER_CASE.getValue();
        String lower = SmartBankEliteConstants.LOWER_CASE.getValue();
        String numbers = SmartBankEliteConstants.NUMBERS.getValue();
        String special = SmartBankEliteConstants.SPECIAL_CHARS.getValue();

        String all = upper + lower + numbers + special;

        StringBuilder password = new StringBuilder();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        for (int i = 4; i < 10; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        return password.toString();
    }

    public static String generateAccountNumber() {
        return "SB" + "EX001" + 1000000 + random.nextInt(9000000);
    }

}