package org.bartoszwojcik.hydropol.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8090")
public class UsersController {
    private final UserService userService;

    // PUT /users/{username}/promote
    @PutMapping("/{username}/promote")
    //@PreAuthorize("hasAnyAuthority('OWNER')")
    @ResponseStatus(HttpStatus.OK)
    public UserDto increaseUserRole(@PathVariable String username) {
        return userService.increaseRole(username);
    }

    // PUT /users/{username}/demote
    @PutMapping("/{username}/demote")
    @ResponseStatus(HttpStatus.OK)
    public UserDto decreaseUserRole(@PathVariable String username) {
        return userService.decreaseRole(username);
    }

    // PUT /users/{username}/assign-city/{cityName}
    @PutMapping("/{username}/assign-city/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public String setCity(@PathVariable String username,
                       @PathVariable String cityName) {
        return userService.setCity(username, cityName);
    }

    // GET /users/all
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
