package org.bartoszwojcik.hydropol.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.user.login.UserLoginRequest;
import org.bartoszwojcik.hydropol.dto.user.login.UserLoginResponseDto;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterRequest;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterResponseDto;
import org.bartoszwojcik.hydropol.security.AuthenticationService;
import org.bartoszwojcik.hydropol.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for user authentication and registration endpoints.
 * <p>
 * Provides endpoints for user registration and login.
 * </p>
 *
 * @author
 * Bartosz Wojcik (or replace as needed)
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    /** Service handling user registration logic */
    private final UserService userService;

    /** Service handling user authentication logic */
    private final AuthenticationService authenticationService;

    /**
     * Registers a new user in the system.
     *
     * @param userRegisterRequest the registration request containing user data; validated automatically
     * @return the response DTO containing registration result information
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto registerUser(
            @Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }

    /**
     * Authenticates an existing user and returns login information.
     *
     * @param userLoginRequest the login request containing username and password; validated automatically
     * @return the response DTO containing authentication token and user details
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserLoginResponseDto loginUser(
            @Valid @RequestBody UserLoginRequest userLoginRequest) {
        return authenticationService.authenticate(userLoginRequest);
    }
}
