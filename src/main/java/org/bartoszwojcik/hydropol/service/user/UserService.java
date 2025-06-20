package org.bartoszwojcik.hydropol.service.user;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterRequest;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterResponseDto;
import org.springframework.data.domain.Pageable;

/**
 * @brief Interface for user-related operations.
 */
public interface UserService {

    /**
     * @brief Registers a new user.
     *
     * @param userRegisterRequest DTO containing user registration data.
     * @return DTO containing registration response details.
     */
    UserRegisterResponseDto register(UserRegisterRequest userRegisterRequest);

    /**
     * @brief Increases the role/privileges of a user.
     *
     * @param username Username of the user whose role is to be increased.
     * @return Updated UserDto with new role.
     */
    UserDto increaseRole(String username);

    /**
     * @brief Decreases the role/privileges of a user.
     *
     * @param username Username of the user whose role is to be decreased.
     * @return Updated UserDto with new role.
     */
    UserDto decreaseRole(String username);

    /**
     * @brief Sets the city for a given user.
     *
     * @param username Username of the user.
     * @param cityName Name of the city to assign to the user.
     * @return Confirmation message or status.
     */
    String setCity(String username, String cityName);

    /**
     * @brief Retrieves all users with pagination.
     *
     * @param pageable Pageable object to specify pagination details.
     * @return List of UserDto objects.
     */
    List<UserDto> findAll(Pageable pageable);
}
