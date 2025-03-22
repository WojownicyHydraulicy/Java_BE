package org.bartoszwojcik.hydropol.controller;

import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PutMapping(value = "/{username}/promotion")
    //@PreAuthorize("hasAnyAuthority('OWNER')")
    @ResponseStatus(HttpStatus.OK)
    public UserDto increaseUserRole(@PathVariable String username) {
        return userService.increaseRole(username);
    }

    @PutMapping(value = "/{username}/degrade")
    @ResponseStatus(HttpStatus.OK)
    public UserDto decreaseUserRole(@PathVariable String username) {
        return userService.decreaseRole(username);
    }

    @PutMapping(value = "/{username}/city/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public String setCity(@PathVariable String username,
                       @PathVariable String cityName) {
        return userService.setCity(username, cityName);
    }
}
