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

/**
 * Serwis odpowiedzialny za uwierzytelnianie użytkowników.
 * Realizuje proces logowania, generuje token JWT dla poprawnie uwierzytelnionych użytkowników.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    /**
     * Uwierzytelnia użytkownika na podstawie danych logowania.
     * Wykorzystuje mechanizm Spring Security do weryfikacji poprawności loginu i hasła.
     * Po pomyślnym uwierzytelnieniu generuje token JWT na podstawie adresu email użytkownika.
     *
     * @param userLoginRequest DTO zawierające email i hasło użytkownika
     * @return DTO zawierające token JWT
     * @throws UsernameNotFoundException gdy nie uda się znaleźć użytkownika w bazie
     */
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
