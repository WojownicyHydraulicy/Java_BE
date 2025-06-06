package org.bartoszwojcik.hydropol.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.service.employee.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // GET /employees/all
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeService.findAll(pageable);
    }
}
