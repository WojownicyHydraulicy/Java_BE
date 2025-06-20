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

/**
 * REST controller for managing users.
 * <p>
 * Provides endpoints for promoting/demoting user roles, assigning cities, and retrieving users.
 * </p>
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsersController {

    /** Service for user-related operations */
    private final UserService userService;

    /**
     * Promotes the role of a user identified by username.
     *
     * @param username the username of the user to promote
     * @return updated user DTO with increased role
     */
    @PutMapping("/{username}/promote")
    // @PreAuthorize("hasAnyAuthority('OWNER')") // Uncomment if role-based access control is needed
    @ResponseStatus(HttpStatus.OK)
    public UserDto increaseUserRole(@PathVariable String username) {
        return userService.increaseRole(username);
    }

    /**
     * Demotes the role of a user identified by username.
     *
     * @param username the username of the user to demote
     * @return updated user DTO with decreased role
     */
    @PutMapping("/{username}/demote")
    @ResponseStatus(HttpStatus.OK)
    public UserDto decreaseUserRole(@PathVariable String username) {
        return userService.decreaseRole(username);
    }

    /**
     * Assigns a city to a user.
     *
     * @param username the username of the user
     * @param cityName the name of the city to assign
     * @return confirmation message or result string
     */
    @PutMapping("/{username}/assign-city/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public String setCity(@PathVariable String username,
                          @PathVariable String cityName) {
        return userService.setCity(username, cityName);
    }

    /**
     * Retrieves a paginated list of all users.
     *
     * @param pageable pagination information
     * @return list of user DTOs
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
