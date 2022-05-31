package com.epam.cv.service;

import com.epam.cv.dto.MessageCreateDto;
import com.epam.cv.entity.Message;
import com.epam.cv.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MessageService {

    Message findMessageById(String Id);

    Message createMessage(MessageCreateDto messageCreateDto, MultipartFile file) throws IOException;

    List<Message> getInboxMessages();

    List<Message> getOutgoingMessages();

}
