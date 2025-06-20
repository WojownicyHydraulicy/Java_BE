package org.bartoszwojcik.hydropol.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtr JWT odpowiedzialny za weryfikację tokenów JWT przesłanych w nagłówku Authorization.
 * Jeśli token jest ważny, ustawia kontekst bezpieczeństwa z uwierzytelnionym użytkownikiem.
 *
 * Działa raz na każde żądanie HTTP (extends OncePerRequestFilter).
 */
@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Metoda filtrująca każde żądanie HTTP.
     * Sprawdza, czy w nagłówku Authorization znajduje się token JWT,
     * a jeśli jest on poprawny, ustawia Authentication w SecurityContext.
     *
     * @param request obiekt żądania HTTP
     * @param response obiekt odpowiedzi HTTP
     * @param filterChain łańcuch filtrów
     * @throws ServletException w przypadku błędu podczas filtrowania
     * @throws IOException w przypadku błędu I/O podczas filtrowania
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        if (token != null && jwtUtil.isValidToken(token)) {
            String username = jwtUtil.getEmail(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Pobiera token JWT z nagłówka Authorization.
     * Oczekuje tokenu w formacie: "Bearer <token>".
     *
     * @param request żądanie HTTP
     * @return token JWT lub null, jeśli token nie istnieje lub ma niepoprawny format
     */
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
