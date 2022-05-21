package com.epam.cv.service;

import com.epam.cv.dto.UserCreateDto;
import com.epam.cv.entity.AuthBody;
import com.epam.cv.entity.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    User createUser(UserCreateDto userCreateDto);

    List<User> getUsers();

    User getCurrentUser();

}
