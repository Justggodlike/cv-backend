package com.epam.cv.controller;

import com.epam.cv.dto.MessageCreateDto;
import com.epam.cv.entity.AuthBody;
import com.epam.cv.entity.Message;
import com.epam.cv.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/messages/inbox")
    public List<Message> getInbox() {
        return messageService.getInboxMessages();
    }

    @GetMapping("/messages/outgoing")
    public List<Message> getOutgoing() {
        return messageService.getOutgoingMessages();
    }

    @PutMapping("/messages/create")
    public Message createMessage(@RequestBody MessageCreateDto messageCreateDto,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        return messageService.createMessage(messageCreateDto, file);
    }
}
