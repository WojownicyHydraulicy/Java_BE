package org.bartoszwojcik.hydropol.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Encja reprezentująca zamówienie.
 *
 * Zawiera informacje o statusie zamówienia, danych kontaktowych,
 * adresie, stopniu trudności usterki, przypisanym użytkowniku,
 * dacie utworzenia oraz dodatkowych informacjach jak zdjęcie i cena.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    /**
     * Unikalny identyfikator zamówienia.
     */
    @Id
    @Column(name = "order_id")
    private String id;

    /**
     * Status zamówienia (np. "W trakcie", "Zakończone").
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * Imię lub nazwa osoby zamawiającej.
     */
    private String name;

    /**
     * Numer telefonu osoby zamawiającej.
     */
    private String telephone;

    /**
     * Miasto, w którym ma być realizowane zamówienie.
     */
    private String city;

    /**
     * Ulica adresu zamówienia.
     */
    private String street;

    /**
     * Kod pocztowy adresu.
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * Numer domu lub mieszkania.
     */
    @Column(name = "house_nr")
    private String houseNr;

    /**
     * Stopień trudności usterki.
     */
    @Column(name = "defect_difficulty")
    private String defectDifficulty;

    /**
     * Opis problemu lub zamówienia.
     */
    private String description;

    /**
     * Użytkownik (pracownik), któremu przypisano realizację zamówienia.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedTo;

    /**
     * Data utworzenia zamówienia (w formacie tekstowym).
     */
    @Column(name = "created_date")
    private String createdDate;

    /**
     * URL zdjęcia związany z zamówieniem.
     */
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * Cena za realizację zamówienia (w formacie tekstowym).
     */
    private String price;
}
