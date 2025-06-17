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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto registerUser(
            @Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserLoginResponseDto loginUser(
            @Valid @RequestBody UserLoginRequest userLoginRequest) {
        return authenticationService.authenticate(userLoginRequest);
    }
}
