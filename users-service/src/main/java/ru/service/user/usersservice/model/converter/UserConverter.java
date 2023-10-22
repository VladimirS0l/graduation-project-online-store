package ru.service.user.usersservice.model.converter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.service.user.usersservice.model.User;
import ru.service.user.usersservice.model.dto.UserDto;

@Getter
@Setter
@Component
public class UserConverter {
    public UserDto entityToDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .confirmPassword(user.getConfirmPassword())
                .build();
    }

    public User dtoToUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .confirmPassword(userDto.getConfirmPassword())
                .build();
    }

}
