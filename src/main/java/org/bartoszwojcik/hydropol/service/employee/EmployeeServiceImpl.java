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

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository employeeUserRepository;
    private final OrderRepository orderRepository;
    private final EmployeeMapper employeeMapper;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    @Override
    public List<EmployeeDto> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).stream()
                .map(employeeMapper::toDto)
                .toList();
    }

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

    @Override
    public EmployeeDto workerUnavailability(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Employee employee = employeeRepository.findByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        if (employee.getAvailability() < 0
                    || orderRepository.existsOrderByAssignedTo(user)) {
            throw new RuntimeException("Employee has no availability");
        }
        employee.setAvailability(0);
        return employeeMapper.toDto(
                employeeRepository.save(employee)
        );
    }

    @Override
    public EmployeeDto workerAvailability(String email, Integer availability) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Employee employee = employeeRepository.findByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        if (employee.getAvailability() > 0
                    || !orderRepository.existsOrderByAssignedTo(user)) {
            throw new RuntimeException("Employee has availability");
        }
        employee.setAvailability(availability);
        return employeeMapper.toDto(
                employeeRepository.save(employee)
        );
    }

}
