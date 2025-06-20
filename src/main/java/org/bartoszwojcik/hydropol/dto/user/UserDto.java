package org.bartoszwojcik.hydropol.dto.user;

import lombok.Data;

/**
 * Data Transfer Object representing a user.
 */
@Data
public class UserDto {

    /** Unique identifier of the user */
    private Long id;

    /** Username of the user */
    private String username;

    /** First name of the user */
    private String firstName;

    /** Last name of the user */
    private String lastName;

    /** Email address of the user */
    private String email;

    /** Role assigned to the user */
    private String role;
}
