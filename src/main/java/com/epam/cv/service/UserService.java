package com.epam.cv.service;

import com.epam.cv.dto.FileDto;
import com.epam.cv.dto.MessageDto;
import com.epam.cv.dto.UserCreateDto;
import com.epam.cv.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    User createUser(UserCreateDto userCreateDto);

    List<User> getUsers();

    User getCurrentUser();

    String saveFile() throws IOException;

    FileDto serveFile(String fileId);

}
