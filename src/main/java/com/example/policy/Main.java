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

    /**
     * Demonstration method containing intentional Checkstyle violations.
     *
     * <p>This method is never called by the application. It exists only
     * to demonstrate how Checkstyle detects source-code violations and
     * how Warnings Next Generation displays them in Jenkins.</p>
     */
    private static void bad_method_name() {
        int BadVariable = 10;
        String wrong_variable = "demo";
        boolean BAD_FLAG = true;

        System.out.println(BadVariable + wrong_variable + BAD_FLAG);
    }

    /**
     * Demonstration method containing additional intentional
     * Checkstyle violations.
     *
     * <p>This method is also never called. It is kept in the source only
     * for educational purposes so that Jenkins can show several
     * Checkstyle findings in the report.</p>
     */
    private static void another_bad_method() {
        int anotherBadVariable = 42;
        String BAD_NAME = "example";
        boolean wrong_flag = false;

        System.out.println(
                anotherBadVariable + BAD_NAME + wrong_flag);
    }
}
