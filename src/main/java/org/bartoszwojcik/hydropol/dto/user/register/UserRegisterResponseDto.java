package org.bartoszwojcik.hydropol.dto.user.register;

import lombok.Data;

/**
 * Response DTO returned after successful user registration.
 */
@Data
public class UserRegisterResponseDto {

    /** Registered username */
    private String username;

    /** Registered user's first name */
    private String firstName;

    /** Registered user's email address */
    private String email;
}
