package org.bartoszwojcik.hydropol.dto.user.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * Request DTO for user login.
 *
 * @param email    User's email address, must be a valid non-empty email
 * @param password User's password, must be non-empty
 */
public record UserLoginRequest(
        @NotEmpty
        @Email
        String email,

        @NotEmpty
        String password
) { }
