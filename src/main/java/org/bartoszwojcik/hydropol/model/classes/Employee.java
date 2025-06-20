package org.bartoszwojcik.hydropol.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bartoszwojcik.hydropol.model.enums.UserRole;

/**
 * Encja reprezentująca pracownika.
 *
 * Zawiera powiązanie z użytkownikiem, poziom dostępności, rolę pracownika oraz przypisane miasto.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    /**
     * Unikalny identyfikator pracownika.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Powiązany użytkownik, który reprezentuje danego pracownika.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Dostępność pracownika (np. liczba godzin lub jednostek dostępności).
     * Nie może być pusta.
     */
    @Column(nullable = false)
    private Integer availability;

    /**
     * Rola pracownika określona w enumeracji {@link UserRole}.
     * Przechowywana jako wartość tekstowa.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole workerRole;

    /**
     * Nazwa miasta, do którego pracownik jest przypisany.
     * Może być pusta (opcjonalne pole).
     */
    private String city;
}
