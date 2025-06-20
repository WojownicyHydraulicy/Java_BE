package org.bartoszwojcik.hydropol.service.employee;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.mapper.EmployeeMapper;
import org.bartoszwojcik.hydropol.mapper.OrderMapper;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.bartoszwojcik.hydropol.repository.employee.EmployeeRepository;
import org.bartoszwojcik.hydropol.repository.order.OrderRepository;
import org.bartoszwojcik.hydropol.repository.user.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link EmployeeService} providing business logic
 * related to employee management.
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository employeeUserRepository;
    private final OrderRepository orderRepository;
    private final EmployeeMapper employeeMapper;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    /**
     * Retrieves a paginated list of all employees.
     *
     * @param pageable pagination parameters
     * @return list of employee DTOs
     */
    @Override
    public List<EmployeeDto> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    /**
     * Finds all orders assigned to the employee identified by their email.
     *
     * @param email employee's email
     * @param pageable pagination parameters
     * @return list of order DTOs assigned to the employee
     * @throws RuntimeException if employee is not found
     */
    @Override
    public List<OrderDto> findEmployeeOrders(String email, Pageable pageable) {
        return employeeUserRepository.findByUsername(email, pageable)
                .stream()
                .map(user -> employeeRepository.findByUserId(user.getId())
                        .orElseThrow(() -> new RuntimeException("Employee not found")))
                .flatMap(employee -> orderRepository.findByAssignedToId(employee.getId()).stream())
                .map(orderMapper::toDto)
                .toList();
    }

    /**
     * Sets the availability of the employee with the given email to unavailable (0),
     * if they have availability and no assigned orders.
     *
     * @param email employee's email
     * @return updated employee DTO with availability set to 0
     * @throws RuntimeException if user or employee is not found,
     *                          or employee has no availability to set unavailable
     */
    @Override
    public EmployeeDto workerUnavailability(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Employee employee = employeeRepository.findByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        if (employee.getAvailability() < 0 || orderRepository.existsOrderByAssignedTo(user)) {
            throw new RuntimeException("Employee has no availability");
        }
        employee.setAvailability(0);
        return employeeMapper.toDto(
                employeeRepository.save(employee)
        );
    }

    /**
     * Sets the availability of the employee with the given email to the specified value,
     * if currently unavailable and has assigned orders.
     *
     * @param email employee's email
     * @param availability new availability value
     * @return updated employee DTO with the new availability
     * @throws RuntimeException if user or employee is not found,
     *                          or employee already has availability set,
     *                          or no assigned orders found
     */
    @Override
    public EmployeeDto workerAvailability(String email, Integer availability) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Employee employee = employeeRepository.findByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        if (employee.getAvailability() > 0 || !orderRepository.existsOrderByAssignedTo(user)) {
            throw new RuntimeException("Employee has availability");
        }
        employee.setAvailability(availability);
        return employeeMapper.toDto(
                employeeRepository.save(employee)
        );
    }
}
