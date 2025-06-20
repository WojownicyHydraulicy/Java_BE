package org.bartoszwojcik.hydropol.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Encja reprezentująca miasto.
 *
 * Przechowuje informacje o nazwie miasta, kraju oraz strefie czasowej.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cities")
public class City {

    /**
     * Unikalny identyfikator miasta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa miasta.
     * Musi być unikalna oraz nie może być pusta.
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Kraj, w którym znajduje się miasto.
     * Nie może być puste.
     */
    @Column(nullable = false)
    private String country;

    /**
     * Strefa czasowa miasta.
     * Nie może być pusta.
     */
    @Column(nullable = false)
    private String timezone;
}
