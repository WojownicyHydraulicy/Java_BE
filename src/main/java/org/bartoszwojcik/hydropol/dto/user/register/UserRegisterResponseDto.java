package org.bartoszwojcik.hydropol.dto.user.register;

import lombok.Data;

@Data
public class UserRegisterResponseDto {
    private String username;
    private String firstName;
    private String email;
}
