package com.example.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/** Tests for {@link ValidationResult}. */
class ValidationResultTest {

    @Test
    void shouldStoreValidResult() {
        ValidationResult result = new ValidationResult(true, List.of());

        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
        assertEquals("Password is valid.", result.toString());
    }

    @Test
    void shouldStoreValidationErrors() {
        ValidationResult result = new ValidationResult(
                false,
                List.of("Password is too short.", "Password requires a digit."));

        assertTrue(!result.isValid());
        assertEquals(2, result.getErrors().size());
        assertEquals(
                "Password is invalid: Password is too short.; Password requires a digit.",
                result.toString());
    }

    @Test
    void shouldExposeImmutableErrorList() {
        ValidationResult result = new ValidationResult(
                false,
                List.of("Password is too short."));

        assertThrows(
                UnsupportedOperationException.class,
                () -> result.getErrors().add("Another error"));
    }

    @Test
    void shouldCopyInputList() {
        List<String> errors = new ArrayList<>();
        errors.add("Password is too short.");

        ValidationResult result = new ValidationResult(false, errors);
        errors.add("Another error.");

        assertEquals(1, result.getErrors().size());
    }
}
