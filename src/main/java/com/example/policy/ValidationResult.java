package com.example.policy;

import java.util.List;

/**
 * Represents the result of password validation.
 *
 * <p>A valid result contains no validation errors.</p>
 */
public final class ValidationResult {

    private final boolean valid;
    private final List<String> errors;

    /**
     * Creates a validation result.
     *
     * @param valid whether the password is valid
     * @param errors validation errors
     */
    public ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = List.copyOf(errors);
    }

    /**
     * Returns whether the password is valid.
     *
     * @return true when the password satisfies the policy
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Returns validation errors.
     *
     * @return immutable list of validation errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Returns a human-readable validation result.
     *
     * @return validation result description
     */
    @Override
    public String toString() {
        if (valid) {
            return "Password is valid.";
        }
        return "Password is invalid: " + String.join("; ", errors);
    }
}
