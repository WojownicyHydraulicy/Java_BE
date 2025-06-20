package org.bartoszwojcik.hydropol.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation used to validate the strength of a password.
 * <p>
 * The actual validation logic is handled by {@link PasswordConstraintValidator}, and ensures that the password:
 * <ul>
 *   <li>Is between 8 and 64 characters long</li>
 *   <li>Contains uppercase and lowercase letters</li>
 *   <li>Includes digits and special characters</li>
 *   <li>Does not contain whitespace</li>
 * </ul>
 * </p>
 *
 * <pre>
 * Example usage:
 * {@code
 * @ValidPassword
 * private String password;
 * }
 * </pre>
 *
 * @see PasswordConstraintValidator
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    /**
     * The default validation message if the password is invalid.
     *
     * @return the error message
     */
    String message() default "Hasło nie spełnia wymagań bezpieczeństwa";

    /**
     * Allows the specification of validation groups.
     *
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * Used by clients to assign custom payload objects to a constraint.
     *
     * @return the payload types
     */
    Class<? extends Payload>[] payload() default {};
}
