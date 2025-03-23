package org.bartoszwojcik.hydropol.service.employee;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    List<EmployeeDto> findAll(Pageable pageable);
}
