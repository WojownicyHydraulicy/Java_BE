package org.bartoszwojcik.hydropol.security;

import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementacja interfejsu UserDetailsService do ładowania użytkownika na podstawie adresu email.
 * Używana przez Spring Security do uzyskania szczegółów użytkownika podczas uwierzytelniania.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Wczytuje użytkownika na podstawie adresu email.
     *
     * @param email adres email użytkownika, używany jako unikalny identyfikator do logowania
     * @return UserDetails zawierający dane użytkownika
     * @throws UsernameNotFoundException jeśli użytkownik o podanym emailu nie istnieje
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
