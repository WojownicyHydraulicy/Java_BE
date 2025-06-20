package org.bartoszwojcik.hydropol.dto.user.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bartoszwojcik.hydropol.annotation.FieldMatch;
import org.bartoszwojcik.hydropol.annotation.ValidPassword;
import org.hibernate.validator.constraints.Length;

/**
 * Request DTO for user registration.
 *
 * Validates that password and confirmPassword fields match.
 */
@Data
@FieldMatch(first = "password", second = "confirmPassword",
        message = "The password fields must match")
public class UserRegisterRequest {

    /** Username of the new user (1-50 characters) */
    @NotBlank
    @Length(min = 1, max = 50)
    private String username;

    /** First name of the user (1-50 characters) */
    @NotBlank
    @Length(min = 1, max = 50)
    private String firstName;

    /** Last name of the user (1-50 characters) */
    @NotBlank
    @Length(min = 1, max = 50)
    private String lastName;

    /** Email address of the user, must be valid */
    @NotBlank
    @Email
    private String email;

    /** Password for the user account, validated by {@link ValidPassword} */
    @NotBlank
    @ValidPassword
    private String password;

    /** Confirmation of the password; must match password */
    @NotBlank
    private String confirmPassword;

    /** City assigned to the user */
    @NotBlank
    private String city;
}
