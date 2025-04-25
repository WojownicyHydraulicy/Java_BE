package org.bartoszwojcik.hydropol.dto.employee;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String userName;
    private Integer availability;
    private String workerRole;
}
