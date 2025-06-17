package org.bartoszwojcik.hydropol.security;

import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.user.login.UserLoginRequest;
import org.bartoszwojcik.hydropol.dto.user.login.UserLoginResponseDto;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.bartoszwojcik.hydropol.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserLoginResponseDto authenticate(UserLoginRequest userLoginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.email(),
                        userLoginRequest.password()
                )
        );
        User user = userRepository.findUserByUsername(authenticate.getName()).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
        String token = jwtUtil.generateToken(user.getEmail());
        return new UserLoginResponseDto(token);
    }
    
}
