package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.dto.user.login.UserLoginResponseDto;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterRequest;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterResponseDto;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserDto toDto(User user);

    UserRegisterResponseDto toRegisterResponseDto(User user);

    UserLoginResponseDto toLoginResponseDto(User user);

    User toUser(UserRegisterRequest userRegisterRequest);
}
