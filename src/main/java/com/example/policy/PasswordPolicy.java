package com.example.policy;

import java.util.Objects;

/**
 * Defines the rules that a password must satisfy.
 *
 * <p>The policy is immutable. Once created, its rules cannot be changed.</p>
 */
public final class PasswordPolicy {

    private final int minLength;
    private final int maxLength;
    private final boolean requireUppercase;
    private final boolean requireLowercase;
    private final boolean requireDigit;
    private final boolean requireSpecial;

    /**
     * Creates a password policy.
     *
     * @param minLength minimum password length in Unicode code points
     * @param maxLength maximum password length in Unicode code points
     * @param requireUppercase whether an uppercase letter is required
     * @param requireLowercase whether a lowercase letter is required
     * @param requireDigit whether a digit is required
     * @param requireSpecial whether a supported special character is required
     * @throws IllegalArgumentException if the length range is invalid
     */
    public PasswordPolicy(
            int minLength,
            int maxLength,
            boolean requireUppercase,
            boolean requireLowercase,
            boolean requireDigit,
            boolean requireSpecial) {
        if (minLength < 1) {
            throw new IllegalArgumentException("Minimum length must be at least 1");
        }
        if (maxLength < minLength) {
            throw new IllegalArgumentException(
                    "Maximum length must not be less than minimum length");
        }

        this.minLength = minLength;
        this.maxLength = maxLength;
        this.requireUppercase = requireUppercase;
        this.requireLowercase = requireLowercase;
        this.requireDigit = requireDigit;
        this.requireSpecial = requireSpecial;
    }

    /**
     * Returns the minimum allowed password length.
     *
     * @return minimum length in Unicode code points
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Returns the maximum allowed password length.
     *
     * @return maximum length in Unicode code points
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Indicates whether uppercase characters are required.
     *
     * @return true if uppercase characters are required
     */
    public boolean isUppercaseRequired() {
        return requireUppercase;
    }

    /**
     * Indicates whether lowercase characters are required.
     *
     * @return true if lowercase characters are required
     */
    public boolean isLowercaseRequired() {
        return requireLowercase;
    }

    /**
     * Indicates whether digits are required.
     *
     * @return true if digits are required
     */
    public boolean isDigitRequired() {
        return requireDigit;
    }

    /**
     * Indicates whether special characters are required.
     *
     * @return true if special characters are required
     */
    public boolean isSpecialRequired() {
        return requireSpecial;
    }

    /**
     * Checks whether another object represents the same policy.
     *
     * @param object object to compare
     * @return true if both objects contain the same policy
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PasswordPolicy other)) {
            return false;
        }
        return minLength == other.minLength
                && maxLength == other.maxLength
                && requireUppercase == other.requireUppercase
                && requireLowercase == other.requireLowercase
                && requireDigit == other.requireDigit
                && requireSpecial == other.requireSpecial;
    }

    /**
     * Returns a hash code for this policy.
     *
     * @return policy hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                minLength,
                maxLength,
                requireUppercase,
                requireLowercase,
                requireDigit,
                requireSpecial);
    }

    /**
     * Returns a human-readable representation of this policy.
     *
     * @return policy description
     */
    @Override
    public String toString() {
        return "PasswordPolicy{"
                + "minLength=" + minLength
                + ", maxLength=" + maxLength
                + ", requireUppercase=" + requireUppercase
                + ", requireLowercase=" + requireLowercase
                + ", requireDigit=" + requireDigit
                + ", requireSpecial=" + requireSpecial
                + '}';
    }
}
