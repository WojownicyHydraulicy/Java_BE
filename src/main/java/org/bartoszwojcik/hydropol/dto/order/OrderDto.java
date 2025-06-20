package org.bartoszwojcik.hydropol.dto.order;

import lombok.Data;

/**
 * Data Transfer Object representing an order.
 */
@Data
public class OrderDto {

    /** Unique identifier of the order */
    private String id;

    /** Current status of the order */
    private String orderStatus;

    /** Name of the person who placed the order */
    private String name;

    /** Contact telephone number */
    private String telephone;

    /** City where the order is to be fulfilled */
    private String city;

    /** Street address for the order */
    private String street;

    /** Postal code for the address */
    private String postCode;

    /** House number */
    private String houseNr;

    /** Difficulty level of the defect to be fixed */
    private String defectDifficulty;

    /** Description of the order or defect */
    private String description;

    /** Username of the employee assigned to the order */
    private String assignedTo;

    /** Date when the order was created (format as String) */
    private String createdDate;
}
