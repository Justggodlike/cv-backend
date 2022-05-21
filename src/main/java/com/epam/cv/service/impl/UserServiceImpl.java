package com.epam.cv.service.impl;

import com.epam.cv.dto.UserCreateDto;
import com.epam.cv.entity.Role;
import com.epam.cv.entity.User;
import com.epam.cv.repository.RoleRepository;
import com.epam.cv.repository.UserRepository;
import com.epam.cv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(UserCreateDto userCreateDto) {
        User userExists = findUserByEmail(userCreateDto.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + userCreateDto.getEmail() + " already exists");
        }
        Role userRole = roleRepository.findByRole("USER");
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userCreateDto.getPassword()))
                .roles(new HashSet<>(Collections.singletonList(userRole)))
                .build();
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return findUserByEmail(currentPrincipalName);
    }
}
