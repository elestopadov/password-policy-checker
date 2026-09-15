package com.example.policy;

/**
 * Command-line entry point for the Password Policy Checker.
 */
public final class Main {

    private Main() {
        // Utility class.
    }

    /**
     * Runs the password validator.
     *
     * @param args first argument is the password to validate
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(
                    "Usage: java -jar password-policy-checker-1.0.0.jar <password>");
            return;
        }

        PasswordPolicy policy = new PasswordPolicy(
                8,
                64,
                true,
                true,
                true,
                true);

        PasswordValidator validator = new PasswordValidator(policy);
        ValidationResult result = validator.validate(args[0]);

        System.out.println(result);
    }
}
