package com.example.policy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validates passwords according to a {@link PasswordPolicy}.
 */
public final class PasswordValidator {

    private static final String SPECIAL_CHARACTERS =
            "!@#$%^&*()-_=+[]{};:,.?/";

    private final PasswordPolicy policy;

    /**
     * Creates a validator for the supplied policy.
     *
     * @param policy password policy
     */
    public PasswordValidator(PasswordPolicy policy) {
        this.policy = Objects.requireNonNull(policy, "policy must not be null");
    }

    /**
     * Validates a password.
     *
     * @param password password to validate
     * @return validation result containing all detected violations
     */
    public ValidationResult validate(String password) {
        List<String> errors = new ArrayList<>();

        if (password == null) {
            errors.add("Password must not be null.");
            return new ValidationResult(false, errors);
        }

        int passwordLength =
                password.codePointCount(0, password.length());

        if (passwordLength < policy.getMinLength()) {
            errors.add(
                    "Password must contain at least "
                            + policy.getMinLength()
                            + " characters.");
        }

        if (passwordLength > policy.getMaxLength()) {
            errors.add(
                    "Password must contain at most "
                            + policy.getMaxLength()
                            + " characters.");
        }

        if (policy.isUppercaseRequired() && !containsUppercase(password)) {
            errors.add("Password must contain an uppercase letter.");
        }

        if (policy.isLowercaseRequired() && !containsLowercase(password)) {
            errors.add("Password must contain a lowercase letter.");
        }

        if (policy.isDigitRequired() && !containsDigit(password)) {
            errors.add("Password must contain a digit.");
        }

        if (policy.isSpecialRequired() && !containsSpecialCharacter(password)) {
            errors.add("Password must contain a special character.");
        }

        return new ValidationResult(errors.isEmpty(), errors);
    }

    private boolean containsUppercase(String password) {
        return password.codePoints().anyMatch(Character::isUpperCase);
    }

    private boolean containsLowercase(String password) {
        return password.codePoints().anyMatch(Character::isLowerCase);
    }

    private boolean containsDigit(String password) {
        return password.codePoints().anyMatch(Character::isDigit);
    }

    private boolean containsSpecialCharacter(String password) {
        return password.codePoints()
                .anyMatch(character -> SPECIAL_CHARACTERS.indexOf(character) >= 0);
    }
}
