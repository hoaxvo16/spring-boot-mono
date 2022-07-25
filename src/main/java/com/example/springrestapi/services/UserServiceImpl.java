package com.example.springrestapi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.entities.UserRole;
import com.example.springrestapi.enums.Role;
import com.example.springrestapi.mappers.UserMapper;
import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.models.UserDto;
import com.example.springrestapi.repositories.UserRepository;
import com.example.springrestapi.repositories.UserRoleRepository;
import com.example.springrestapi.securityImpl.SecurityUser;
import com.example.springrestapi.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDto editUser(UserDto user, UUID id) throws Exception {
        Optional<User> existedUser = userRepository.findById(id);
        if (existedUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User is not existed");
        }
        existedUser.get().setEmail(user.getEmail());
        existedUser.get().setFirstName(user.getFirstName());
        existedUser.get().setLastName(user.getLastName());
        existedUser.get().setPhone(user.getPhone());
        userRepository.save(existedUser.get());
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        User existedUser = userRepository.findByEmail(email);
        if (existedUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with " + email + "  doest not exist");
        }
        return existedUser;
    }

    @Override
    public User getUserByPhoneNumber(String phone) throws Exception {
        User existedUser = userRepository.findByPhone(phone);
        if (existedUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with phone number " + phone + "  doest not exist");
        }
        return existedUser;
    }

    @Override
    public User addNewUser(RegisterUserDto user) throws Exception {
        User newUser = userMapper.toUser(user);
        List<UserRole> roles = userRoleRepository.findAll();
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
        return new SecurityUser(user);
    }

    @Override
    public User getUserByAccountId(UUID accountId) throws Exception {
        Optional<User> user = userRepository.findById(accountId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with account id " + accountId + "  doest not exist");
        }

        return user.get();
    }

}
