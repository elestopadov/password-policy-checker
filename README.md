# Password Policy Checker

Educational Java project demonstrating Maven, JUnit, Javadoc, Checkstyle and executable JAR creation.

## Requirements

- JDK 21
- Apache Maven 3.9+


## Project structure

```text
password-policy-checker/
.
├── config
│   └── checkstyle
│       └── checkstyle.xml
├── LICENSE
├── MANIFEST.MF
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── example
    │               └── policy
    │                   ├── Main.java
    │                   ├── package-info.java
    │                   ├── PasswordPolicy.java
    │                   ├── PasswordValidator.java
    │                   └── ValidationResult.java
    └── test
        └── java
            └── com
                └── example
                    └── policy
                        ├── PasswordPolicyTest.java
                        ├── PasswordValidatorTest.java
                        └── ValidationResultTest.java

```

## Password policy

The default application policy requires:

- minimum length: 8 Unicode code points;
- maximum length: 64 Unicode code points;
- at least one uppercase letter;
- at least one lowercase letter;
- at least one digit;
- at least one special character from `!@#$%^&*()-_=+[]{};:,.?/`.

## Build

```bash
mvn clean package
```

The resulting JAR is created in `target/password-policy-checker-1.0.0.jar`.

## Run

```bash
java -jar target/password-policy-checker-1.0.0.jar "StrongPass1!"
```

Expected output:

```text
Password is valid.
```

Invalid example:

```bash
java -jar target/password-policy-checker-1.0.0.jar "weak"
```

## Tests

Run the 24 unit tests:

```bash
mvn test
```

Surefire writes XML reports to `target/surefire-reports/`.

## Javadoc

```bash
mvn javadoc:javadoc
```

Generated documentation is placed in `target/site/apidocs/`.

## Checkstyle

Checkstyle is intentionally not bound to the Maven default lifecycle in this educational project. Run it explicitly:

```bash
mvn checkstyle:check
```

Rules are version-controlled in `config/checkstyle/checkstyle.xml`. The machine-readable result is written to `target/checkstyle-result.xml`.

The configuration contains a `TodoComment` rule so that the lesson can demonstrate a deliberate quality failure. Add a comment such as `// TODO: refactor this method`, run Checkstyle, observe the failure, then remove the comment and run it again.

## Build results

Typical generated files include:

```text
target/
├── classes/
├── site/apidocs/
├── surefire-reports/
├── test-classes/
├── checkstyle-result.xml
└── password-policy-checker-1.0.0.jar
```

## Notes

`MANIFEST.MF` is kept in the repository for comparison with the earlier manual-JAR stage of the course. 
The Maven JAR Plugin generates the executable JAR manifest from the `mainClass` configured in `pom.xml`; the file is not copied into the JAR by a custom build step.
