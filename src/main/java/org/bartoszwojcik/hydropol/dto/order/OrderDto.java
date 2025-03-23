package org.bartoszwojcik.hydropol.dto.order;

import lombok.Data;

@Data
public class OrderDto {
    private String id;
    private String orderStatus;
    private String name;
    private String telephone;
    private String city;
    private String street;
    private String postCode;
    private String houseNr;
    private String defectDifficulty;
    private String description;
    private String assignedTo;
    private String createdDate;
}
