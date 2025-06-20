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

/**
 * @brief Implementation of UserService interface providing user management operations.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final UserRole USER_ROLE = UserRole.USER;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final CityRepository cityRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * @brief Registers a new user.
     *
     * @param userRegisterRequest Data transfer object containing registration info.
     * @return Response DTO containing registration result.
     * @throws RegistrationException if user with the same email already exists.
     */
    @Override
    public UserRegisterResponseDto register(UserRegisterRequest userRegisterRequest) {
        checkIfUserExist(userRegisterRequest);
        User user = getUser(userRegisterRequest);
        return userMapper.toRegisterResponseDto(
                userRepository.save(user)
        );
    }

    /**
     * @brief Increases the role of the user (USER -> WORKER -> OWNER).
     * Creates Employee entity if role changes to WORKER.
     *
     * @param username Username of the user to upgrade.
     * @return Updated user DTO.
     * @throws EntityNotFoundException if user does not exist.
     */
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

    /**
     * @brief Creates a new Employee entity for a given user.
     *
     * @param user User for whom to create Employee.
     * @return New Employee entity.
     */
    private static Employee getEmployee(User user) {
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setWorkerRole(user.getRole());
        employee.setAvailability(0);
        return employee;
    }

    /**
     * @brief Decreases the role of the user (OWNER -> WORKER -> USER).
     * Deletes Employee entity if role changes from WORKER to USER.
     *
     * @param username Username of the user to downgrade.
     * @return Updated user DTO.
     * @throws EntityNotFoundException if user does not exist.
     */
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
                                employee -> employeeRepository.deleteById(employee.getId())
                        );
            }
        }
        userRepository.save(user);
        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    /**
     * @brief Sets the city for the specified user.
     *
     * @param username Username of the user.
     * @param cityName Name of the city to assign.
     * @return Confirmation message.
     * @throws EntityNotFoundException if user or city does not exist.
     */
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

    /**
     * @brief Retrieves all users with pagination.
     *
     * @param pageable Pageable parameters.
     * @return List of user DTOs.
     */
    @Override
    public List<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * @brief Builds a User entity from registration data.
     *
     * @param userRegisterRequest Registration data.
     * @return New User entity.
     */
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

    /**
     * @brief Checks if a user with the same email already exists.
     *
     * @param userRegisterRequest Registration data.
     * @throws RegistrationException if user with email exists.
     */
    private void checkIfUserExist(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findUserByEmail(userRegisterRequest.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
    }
}
