package com.example.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests for {@link PasswordPolicy}. */
class PasswordPolicyTest {

    @Test
    void shouldCreateValidPolicy() {
        PasswordPolicy policy = new PasswordPolicy(8, 64, true, true, true, true);

        assertEquals(8, policy.getMinLength());
        assertEquals(64, policy.getMaxLength());
        assertTrue(policy.isUppercaseRequired());
        assertTrue(policy.isLowercaseRequired());
        assertTrue(policy.isDigitRequired());
        assertTrue(policy.isSpecialRequired());
    }

    @Test
    void shouldRejectZeroMinimumLength() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PasswordPolicy(0, 64, true, true, true, true));
    }

    @Test
    void shouldRejectMaximumLengthSmallerThanMinimum() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PasswordPolicy(10, 8, true, true, true, true));
    }

    @Test
    void shouldCompareEqualPolicies() {
        PasswordPolicy first = new PasswordPolicy(8, 64, true, true, true, true);
        PasswordPolicy second = new PasswordPolicy(8, 64, true, true, true, true);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldDetectDifferentPolicies() {
        PasswordPolicy first = new PasswordPolicy(8, 64, true, true, true, true);
        PasswordPolicy second = new PasswordPolicy(10, 64, true, true, true, true);

        assertFalse(first.equals(second));
    }
}
