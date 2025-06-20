package org.bartoszwojcik.hydropol.dto.employee;

import lombok.Data;

/**
 * Data Transfer Object representing an employee.
 */
@Data
public class EmployeeDto {

    /** Unique identifier of the employee */
    private Long id;

    /** Username associated with the employee */
    private String userName;

    /** Availability status or metric of the employee */
    private Integer availability;

    /** Role of the employee (e.g., job title) */
    private String workerRole;
}
