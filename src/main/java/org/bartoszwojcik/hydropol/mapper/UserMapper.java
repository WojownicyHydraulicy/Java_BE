package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.dto.user.login.UserLoginResponseDto;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterRequest;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterResponseDto;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link User} entity and various user-related DTOs.
 */
@Mapper(config = MapperConfig.class)
public interface UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the user entity to convert
     * @return the corresponding UserDto
     */
    UserDto toDto(User user);

    /**
     * Converts a {@link User} entity to a {@link UserRegisterResponseDto}.
     *
     * @param user the user entity to convert
     * @return the corresponding UserRegisterResponseDto
     */
    UserRegisterResponseDto toRegisterResponseDto(User user);

    /**
     * Converts a {@link User} entity to a {@link UserLoginResponseDto}.
     *
     * @param user the user entity to convert
     * @return the corresponding UserLoginResponseDto
     */
    UserLoginResponseDto toLoginResponseDto(User user);

    /**
     * Converts a {@link UserRegisterRequest} DTO to a {@link User} entity.
     *
     * @param userRegisterRequest the registration request DTO to convert
     * @return the corresponding User entity
     */
    User toUser(UserRegisterRequest userRegisterRequest);
}
