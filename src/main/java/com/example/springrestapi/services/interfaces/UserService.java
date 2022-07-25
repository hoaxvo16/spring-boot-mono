package com.example.springrestapi.services.interfaces;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.models.UserDto;

public interface UserService extends UserDetailsService {

    UserDto editUser(UserDto user, UUID id) throws Exception;

    User getUserByEmail(String email) throws Exception;

    User getUserByPhoneNumber(String phone) throws Exception;

    User addNewUser(RegisterUserDto user) throws Exception;

    User getUserByAccountId(UUID accountId) throws Exception;

}
