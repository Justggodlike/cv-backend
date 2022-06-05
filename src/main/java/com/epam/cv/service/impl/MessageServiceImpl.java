package com.epam.cv.service.impl;

import com.epam.cv.dto.MessageCreateDto;
import com.epam.cv.entity.Message;
import com.epam.cv.entity.User;
import com.epam.cv.repository.MessageRepository;
import com.epam.cv.service.MessageService;
import com.epam.cv.service.UserService;
import com.epam.cv.service.VacancyService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    VacancyService vacancyService;

    @Override
    public Message findMessageById(String Id) {
        return null;
    }

    @Override
    public Message createMessage(MessageCreateDto messageCreateDto, MultipartFile file) throws IOException {
        return messageRepository.save(Message.builder()
                .desc(messageCreateDto.getDesc())
                .title(messageCreateDto.getTitle())
                .userTo(vacancyService.getVacancyById(messageCreateDto.getId()).getUser())
                .userFrom(userService.getCurrentUser())
                .file(Objects.isNull(file) ? null : file.getBytes())
                .filename(Objects.isNull(file) ? Strings.EMPTY : file.getOriginalFilename())
                .build());
    }

    @Override
    public List<Message> getInboxMessages() {
        User userTo = userService.getCurrentUser();
        return messageRepository.findByUserTo(userTo);
    }

    @Override
    public List<Message> getOutgoingMessages() {
        User userFrom = userService.getCurrentUser();
        List<Message> nado = messageRepository.findByUserFrom(userFrom);
        List<Message> all = messageRepository.findAll();
        return messageRepository.findByUserFrom(userFrom);
    }
}
