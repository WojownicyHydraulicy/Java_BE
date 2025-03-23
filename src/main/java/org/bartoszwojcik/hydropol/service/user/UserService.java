package org.bartoszwojcik.hydropol.service.user;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.user.UserDto;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterRequest;
import org.bartoszwojcik.hydropol.dto.user.register.UserRegisterResponseDto;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserRegisterResponseDto register(UserRegisterRequest userRegisterRequest);

    UserDto increaseRole(String username);

    UserDto decreaseRole(String username);

    String setCity(String username, String cityName);

    List<UserDto> findAll(Pageable pageable);
}
