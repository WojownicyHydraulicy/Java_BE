package org.bartoszwojcik.hydropol.service.employee;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.mapper.EmployeeMapper;
import org.bartoszwojcik.hydropol.repository.employee.EmployeeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).stream()
                .map(employeeMapper::toDto)
                .toList();
    }
}
