package org.bartoszwojcik.hydropol.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Validator for {@link FieldMatch} annotation.
 * <p>
 * Compares two field values in a given object for equality.
 * This is typically used to validate fields like password and confirmPassword.
 * </p>
 *
 * @author
 * Bartosz Wojcik (assuming from package name)
 * @see FieldMatch
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    /** Name of the first field to compare. */
    private String firstFieldName;

    /** Name of the second field to compare. */
    private String secondFieldName;

    /** Error message to use in case of validation failure. */
    private String message;

    /**
     * Initializes the validator with the field names and error message
     * provided in the {@link FieldMatch} annotation.
     *
     * @param constraintAnnotation the annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    /**
     * Validates that the two specified fields have matching values.
     *
     * @param value   the object being validated
     * @param context context in which the constraint is evaluated
     * @return {@code true} if the fields match or are both {@code null}, {@code false} otherwise
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            boolean valid = (firstObj == null && secondObj == null)
                    || (firstObj != null && firstObj.equals(secondObj));

            if (!valid) {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(firstFieldName)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }

            return valid;
        } catch (Exception e) {
            // Swallowing exception to fail gracefully in case of reflection issues
            return false;
        }
    }
}
