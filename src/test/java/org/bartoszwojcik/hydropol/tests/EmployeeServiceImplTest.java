package org.bartoszwojcik.hydropol.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.mapper.EmployeeMapper;
import org.bartoszwojcik.hydropol.mapper.OrderMapper;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.bartoszwojcik.hydropol.model.classes.Order;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.bartoszwojcik.hydropol.repository.employee.EmployeeRepository;
import org.bartoszwojcik.hydropol.repository.order.OrderRepository;
import org.bartoszwojcik.hydropol.repository.user.UserRepository;
import org.bartoszwojcik.hydropol.service.employee.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeServiceImplTest {

    @Mock private EmployeeRepository employeeRepository;
    @Mock private UserRepository userRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private EmployeeMapper employeeMapper;
    @Mock private OrderMapper orderMapper;

    @InjectMocks private EmployeeServiceImpl employeeService;

    private final Pageable pageable = PageRequest.of(0, 10);
    private final User user = new User();
    private final Employee employee = new Employee();
    private final Order order = new Order();
    private final OrderDto orderDto = new OrderDto();
    private final EmployeeDto employeeDto = new EmployeeDto();

    @BeforeEach
    void setUp() {
        user.setId(1L);
        user.setEmail("test@example.com");

        employee.setId(1L);
        employee.setAvailability(1);
        employee.setUser(user);

        order.setId("1");
    }

    @Test
    void findAll_returnsListOfEmployeeDto() {
        when(employeeRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(List.of(employee)));
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        List<EmployeeDto> result = employeeService.findAll(pageable);

        assertEquals(1, result.size());
        verify(employeeRepository).findAll(pageable);
        verify(employeeMapper).toDto(employee);
    }

    @Test
    void findEmployeeOrders_returnsOrderDtos() {
        when(userRepository.findByUsername(user.getEmail(), pageable))
                .thenReturn(List.of(user));
        when(employeeRepository.findByUserId(user.getId()))
                .thenReturn(Optional.of(employee));
        when(orderRepository.findByAssignedToId(employee.getId()))
                .thenReturn(List.of(order));
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        List<OrderDto> result = employeeService.findEmployeeOrders(user.getEmail(), pageable);

        assertEquals(1, result.size());
        verify(orderRepository).findByAssignedToId(employee.getId());
    }

    @Test
    void workerUnavailability_setsAvailabilityToZero_andReturnsDto() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(employeeRepository.findByUserId(user.getId())).thenReturn(Optional.of(employee));
        when(orderRepository.existsOrderByAssignedTo(user)).thenReturn(false);
        when(employeeRepository.save(any())).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.workerUnavailability(user.getEmail());

        assertEquals(employeeDto, result);
        assertEquals(0, employee.getAvailability());
    }

    @Test
    void workerUnavailability_throwsIfNegativeAvailabilityOrActiveOrders() {
        employee.setAvailability(-1);
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(employeeRepository.findByUserId(user.getId())).thenReturn(Optional.of(employee));

        assertThrows(RuntimeException.class,
                () -> employeeService.workerUnavailability(user.getEmail()));
    }

    @Test
    void workerAvailability_setsNewAvailability() {
        employee.setAvailability(0);

        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(employeeRepository.findByUserId(user.getId())).thenReturn(Optional.of(employee));
        when(orderRepository.existsOrderByAssignedTo(user)).thenReturn(true);
        when(employeeRepository.save(any())).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.workerAvailability(user.getEmail(), 3);

        assertEquals(employeeDto, result);
        assertEquals(3, employee.getAvailability());
    }

    @Test
    void workerAvailability_throwsIfAlreadyAvailableOrNoOrders() {
        employee.setAvailability(2);

        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(employeeRepository.findByUserId(user.getId())).thenReturn(Optional.of(employee));

        assertThrows(RuntimeException.class,
                () -> employeeService.workerAvailability(user.getEmail(), 5));
    }

    @Test
    void workerUnavailability_throwsIfUserNotFound() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> employeeService.workerUnavailability(user.getEmail()));
    }

    @Test
    void workerAvailability_throwsIfEmployeeNotFound() {
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(employeeRepository.findByUserId(user.getId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> employeeService.workerAvailability(user.getEmail(), 2));
    }
}
