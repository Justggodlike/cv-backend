package com.epam.cv.service.impl;

import com.epam.cv.dto.FileDto;
import com.epam.cv.dto.MessageDto;
import com.epam.cv.dto.UserCreateDto;
import com.epam.cv.entity.Message;
import com.epam.cv.entity.Role;
import com.epam.cv.entity.User;
import com.epam.cv.repository.MessageRepository;
import com.epam.cv.repository.RoleRepository;
import com.epam.cv.repository.UserRepository;
import com.epam.cv.service.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    String uploadPath = "C:\\D\\Trash";

    @Autowired
    private MessageRepository messageRepository;

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

    //MultipartFile file, MessageDto messageDto
    public String saveFile() throws IOException {
//            File uploadDir = new File(uploadPath);
//
//            if(!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(resultFilename));

//            DBObject metaData = new BasicDBObject();
//            metaData.put("type", "image");
//            InputStream inputStream = new FileInputStream(uploadPath + file.getOriginalFilename());
//            String fileId = gridFsOperations.store(inputStream, "logo.png", "image/png", metaData).getId().toString();
//            System.out.println("File id stored : " + fileId);

        Path path = Paths.get("C:/Users/sasha/Desktop/TEST.docx");
        String name = "TEST.docx";
        String originalFileName = "TEST.docx";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

            Message message = Message.builder()
                    .userTo(getCurrentUser())
                    .userFrom(getCurrentUser())
                    .desc("")
                    .title("")
                    .file(result.getBytes())
                    .filename(name)
                    .build();

        return messageRepository.save(message).getId();
    }

    public FileDto serveFile(String fileId) {
        Message message = messageRepository.findById(fileId).get();
       return FileDto.builder().bytes(message.getFile()).name(message.getFilename()).build();
    }
}
