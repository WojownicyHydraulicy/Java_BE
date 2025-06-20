package org.bartoszwojcik.hydropol.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

/**
 * Validator for the {@link ValidPassword} annotation.
 * <p>
 * Enforces password strength using the Passay library.
 * The password must:
 * <ul>
 *   <li>Be between 8 and 64 characters long</li>
 *   <li>Contain at least one uppercase letter</li>
 *   <li>Contain at least one lowercase letter</li>
 *   <li>Contain at least one digit</li>
 *   <li>Contain at least one special character</li>
 *   <li>Not contain whitespace</li>
 * </ul>
 * </p>
 *
 * @see ValidPassword
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    /**
     * Validates a given password string against security rules.
     *
     * @param password the password to validate
     * @param context  the context in which the constraint is evaluated
     * @return {@code true} if the password meets all defined rules, {@code false} otherwise
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 64),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        // Disable the default violation message and provide custom feedback
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                String.join(", ", validator.getMessages(result))
        ).addConstraintViolation();

        return false;
    }
}
