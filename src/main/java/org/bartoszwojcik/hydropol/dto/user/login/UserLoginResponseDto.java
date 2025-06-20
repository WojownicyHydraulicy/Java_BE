package org.bartoszwojcik.hydropol.dto.user.login;

/**
 * Response DTO for user login.
 *
 * @param token JWT or authentication token returned after successful login
 */
public record UserLoginResponseDto(
        String token
) { }
