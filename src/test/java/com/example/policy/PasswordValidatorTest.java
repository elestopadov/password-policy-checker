package com.example.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for {@link PasswordValidator}. */
class PasswordValidatorTest {

    @Test
    void shouldRejectNullPolicy() {
        assertThrows(
                NullPointerException.class,
                () -> new PasswordValidator(null));
    }

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        PasswordPolicy policy = new PasswordPolicy(8, 64, true, true, true, true);
        validator = new PasswordValidator(policy);
    }

    @Test
    void shouldAcceptValidPassword() {
        ValidationResult result = validator.validate("StrongPass1!");

        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void shouldRejectNullPassword() {
        ValidationResult result = validator.validate(null);

        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
    }

    @Test
    void shouldRejectPasswordThatIsTooShort() {
        ValidationResult result = validator.validate("Aa1!");

        assertFalse(result.isValid());
        assertTrue(
                result.getErrors()
                        .contains("Password must contain at least 8 characters."));
    }

    @Test
    void shouldAcceptPasswordAtMinimumLength() {
        ValidationResult result = validator.validate("Abcdef1!");

        assertTrue(result.isValid());
    }

    @Test
    void shouldAcceptPasswordAtMaximumLength() {
        String password = "A" + "a".repeat(61) + "1!";

        assertEquals(64, password.codePointCount(0, password.length()));
        assertTrue(validator.validate(password).isValid());
    }

    @Test
    void shouldRejectPasswordThatIsTooLong() {
        String password = "A" + "a".repeat(62) + "1!";

        assertEquals(65, password.codePointCount(0, password.length()));

        ValidationResult result = validator.validate(password);

        assertFalse(result.isValid());
        assertTrue(
                result.getErrors()
                        .contains("Password must contain at most 64 characters."));
    }

    @Test
    void shouldRejectPasswordWithoutUppercase() {
        ValidationResult result = validator.validate("lowercase1!");

        assertFalse(result.isValid());
        assertTrue(
                result.getErrors()
                        .contains("Password must contain an uppercase letter."));
    }

    @Test
    void shouldRejectPasswordWithoutLowercase() {
        ValidationResult result = validator.validate("UPPERCASE1!");

        assertFalse(result.isValid());
        assertTrue(
                result.getErrors()
                        .contains("Password must contain a lowercase letter."));
    }

    @Test
    void shouldRejectPasswordWithoutDigit() {
        ValidationResult result = validator.validate("NoDigits!!");

        assertFalse(result.isValid());
        assertTrue(
                result.getErrors()
                        .contains("Password must contain a digit."));
    }

    @Test
    void shouldRejectPasswordWithoutSpecialCharacter() {
        ValidationResult result = validator.validate("NoSpecial1");

        assertFalse(result.isValid());
        assertTrue(
                result.getErrors()
                        .contains("Password must contain a special character."));
    }

    @Test
    void shouldReportSeveralViolationsAtOnce() {
        ValidationResult result = validator.validate("abc");

        assertFalse(result.isValid());
        assertEquals(4, result.getErrors().size());
    }

    @Test
    void shouldAllowDisabledPolicyRules() {
        PasswordPolicy policy = new PasswordPolicy(4, 20, false, false, false, false);
        PasswordValidator relaxedValidator = new PasswordValidator(policy);

        assertTrue(relaxedValidator.validate("abcd").isValid());
    }


    @Test
    void shouldCountSupplementaryUnicodeCharacterAsOneCharacter() {
        PasswordPolicy policy = new PasswordPolicy(1, 1, false, false, false, false);
        PasswordValidator unicodeValidator = new PasswordValidator(policy);

        assertTrue(unicodeValidator.validate("😀").isValid());
    }

    @Test
    void shouldRecognizeSupportedSpecialCharacter() {
        assertTrue(validator.validate("Password1?").isValid());
    }
}
