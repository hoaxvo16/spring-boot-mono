package com.example.springrestapi.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.models.UserDto;

@Component
public class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User toUser(UserDto userDto) {
        return new User(userDto.getLastName(), userDto.getEmail(), userDto.getPhone(), userDto.getFirstName());
    }

    public User toUser(RegisterUserDto userDto) {
        User newUser = new User(userDto.getLastName(), userDto.getEmail(), userDto.getPhone(), userDto.getFirstName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return newUser;
    }

    public UserDto toUserDto(RegisterUserDto userDto) {
        return new UserDto(userDto.getLastName(), userDto.getEmail(), userDto.getPhone(), userDto.getFirstName());
    }
}
