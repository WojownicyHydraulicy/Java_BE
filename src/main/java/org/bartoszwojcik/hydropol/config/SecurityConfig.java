package org.bartoszwojcik.hydropol.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration for the application.
 * <p>
 * This configuration:
 * <ul>
 *   <li>Disables CORS and CSRF protection</li>
 *   <li>Allows unauthenticated access to endpoints under {@code /auth/**} and {@code /error}</li>
 *   <li>Requires authentication for all other endpoints</li>
 *   <li>Uses HTTP Basic authentication</li>
 *   <li>Registers a {@link PasswordEncoder} and a custom {@link UserDetailsService}</li>
 * </ul>
 * </p>
 *
 * @see org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 * @see org.springframework.security.web.SecurityFilterChain
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Service for loading user-specific data used during authentication.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Provides a {@link PasswordEncoder} bean using BCrypt hashing algorithm.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the security filter chain and HTTP security rules.
     *
     * @param http the {@link HttpSecurity} object to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception in case of configuration errors
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .build();
    }

    /**
     * Exposes the {@link AuthenticationManager} bean for use in authentication workflows.
     *
     * @param authenticationConfiguration the authentication configuration
     * @return the {@link AuthenticationManager}
     * @throws Exception in case of failure to obtain the manager
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
