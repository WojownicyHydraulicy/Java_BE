package org.bartoszwojcik.hydropol.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    private String id;

    @Column(name = "order_status")
    private String orderStatus;

    private String name;

    private String telephone;

    private String city;

    private String street;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "house_nr")
    private String houseNr;

    @Column(name = "defect_difficulty")
    private String defectDifficulty;

    private String description;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "created_date")
    private String createdDate;

}
