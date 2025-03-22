package org.bartoszwojcik.hydropol.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}

