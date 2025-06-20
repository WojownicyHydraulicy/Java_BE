package org.bartoszwojcik.hydropol.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bartoszwojcik.hydropol.model.enums.UserRole;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Encja reprezentująca użytkownika systemu.
 *
 * Implementuje interfejs {@link UserDetails} z Spring Security
 * w celu integracji z mechanizmem uwierzytelniania.
 *
 * Użytkownicy mogą posiadać rolę {@link UserRole} USER lub WORKER.
 * Wspiera miękkie usuwanie poprzez adnotację {@code @SQLDelete}.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = """
UPDATE users 
SET is_deleted = true
WHERE id = ?
        """)
@Where(clause = "is_deleted = false")
@Table(name = "users")
public class User implements UserDetails {

    /**
     * Unikalny identyfikator użytkownika.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unikalna nazwa użytkownika (login).
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Imię użytkownika.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Nazwisko użytkownika.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Unikalny adres email użytkownika.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Hasło użytkownika (zaszyfrowane).
     */
    @Column(nullable = false)
    private String password;

    /**
     * Rola użytkownika w systemie.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    /**
     * Flaga miękkiego usuwania — oznacza czy użytkownik jest usunięty logicznie.
     */
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    /**
     * Powiązane miasto użytkownika (relacja ManyToOne).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cities_id", referencedColumnName = "id")
    private City citiesId;

    /**
     * Nazwa miasta (prawdopodobnie dla wygody).
     */
    private String city;

    /**
     * Zwraca uprawnienia (role) użytkownika zgodne z Spring Security.
     *
     * @return kolekcja ról użytkownika jako GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role.equals(UserRole.USER)) {
            return List.of(new SimpleGrantedAuthority("USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("WORKER"));
        }
    }

    /**
     * Zwraca informację, czy konto użytkownika nie wygasło.
     * @return zawsze true (konto nie wygasło)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Zwraca informację, czy konto użytkownika nie jest zablokowane.
     * @return zawsze true (konto nie jest zablokowane)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Zwraca informację, czy dane uwierzytelniające użytkownika nie wygasły.
     * @return zawsze true (dane uwierzytelniające są aktualne)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Zwraca informację, czy użytkownik jest aktywny (włączony).
     * @return zawsze true (użytkownik jest aktywny)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
