package org.bartoszwojcik.hydropol.service.user;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterRequest;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterResponseDto;
import org.bartoszwojcik.hydropol.exception.RegistrationException;
import org.bartoszwojcik.hydropol.mapper.UserMapper;
import org.bartoszwojcik.hydropol.model.classes.City;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.bartoszwojcik.hydropol.model.enums.UserRole;
import org.bartoszwojcik.hydropol.repository.city.CityRepository;
import org.bartoszwojcik.hydropol.repository.employee.EmployeeRepository;
import org.bartoszwojcik.hydropol.repository.user.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final UserRole USER_ROLE = UserRole.USER;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final CityRepository cityRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponseDto register(UserRegisterRequest userRegisterRequest) {
        checkIfUserExist(userRegisterRequest);
        User user = getUser(userRegisterRequest);
        return userMapper.toRegisterResponseDto(
                userRepository.save(user)
        );
    }

    @Override
    public UserDto increaseRole(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("user with this username does not exist")
        );
        switch (user.getRole()) {
            case USER -> {
                user.setRole(UserRole.WORKER);
                Employee employee = getEmployee(user);
                employeeRepository.save(employee);
            }
            default -> {
                user.setRole(UserRole.OWNER);
                employeeRepository.updateByUserId(
                        user.getId(), UserRole.OWNER
                );
            }
        }

        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    private static Employee getEmployee(User user) {
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setWorkerRole(user.getRole());
        employee.setAvailability(0);
        return employee;
    }

    @Override
    public UserDto decreaseRole(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("user with this username does not exist")
        );
        switch (user.getRole()) {
            case OWNER -> {
                user.setRole(UserRole.WORKER);
                employeeRepository.updateByUserId(
                        user.getId(), UserRole.WORKER
                );
            }
            default -> {
                user.setRole(UserRole.USER);
                employeeRepository.findByUserId(
                        user.getId())
                        .ifPresent(
                                employee -> employeeRepository
                                        .deleteById(employee
                                                .getId())
                        );
            }
        }
        userRepository.save(user);
        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    @Override
    public String setCity(String username, String cityName) {
        City city = cityRepository.findByName(cityName).orElseThrow(
                () -> new EntityNotFoundException("city with this city name does not exist")
        );
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("user with this username does not exist")
        );
        userRepository.updateCity(user.getId(), city.getId());
        return city.getName() + " from " + city.getCountry() + " added to " + user.getUsername();
    }

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(userMapper::toDto)
                .toList();
    }

    private User getUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(
                passwordEncoder.encode(userRegisterRequest.getPassword())
        );
        user.setCity(userRegisterRequest.getCity());
        user.setRole(USER_ROLE);
        return user;
    }

    private void checkIfUserExist(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findUserByEmail(userRegisterRequest.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
    }
}
