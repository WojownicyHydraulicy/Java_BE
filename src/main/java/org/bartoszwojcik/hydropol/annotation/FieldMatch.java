package org.bartoszwojcik.hydropol.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to validate that two fields of a class have matching values.
 * <p>
 * Typically used for password confirmation or email confirmation fields.
 * </p>
 *
 * <pre>
 * Example usage:
 * {@code
 * @FieldMatch(first = "password", second = "confirmPassword", message = "Passwords do not match")
 * public class UserRegistrationForm {
 *     private String password;
 *     private String confirmPassword;
 *     // getters and setters
 * }
 * }
 * </pre>
 *
 * @see FieldMatchValidator
 */
@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {

    /**
     * Error message to be returned if fields do not match.
     *
     * @return the error message
     */
    String message() default "Fields do not match";

    /**
     * Allows specification of validation groups.
     *
     * @return the group classes
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients to assign custom payload objects to a constraint.
     *
     * @return the payload classes
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * The name of the first field to compare.
     *
     * @return the first field name
     */
    String first();

    /**
     * The name of the second field to compare.
     *
     * @return the second field name
     */
    String second();

    /**
     * Allows multiple {@link FieldMatch} annotations on the same element.
     */
    @Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {

        /**
         * An array of {@link FieldMatch} annotations.
         *
         * @return the array of FieldMatch annotations
         */
        FieldMatch[] value();
    }
}
