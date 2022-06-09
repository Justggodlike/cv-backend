package com.epam.cv.controller;

import com.epam.cv.dto.MessageCreateDto;
import com.epam.cv.entity.AuthBody;
import com.epam.cv.entity.Message;
import com.epam.cv.entity.Vacancy;
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

    @PostMapping("/message/create")
    public Message createMessage(@RequestParam("id") String id,
                                 @RequestParam("title") String title,
                                 @RequestParam("desc") String desc,
                                 @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        return messageService.createMessage(MessageCreateDto.builder().id(id).desc(desc).title(title).build(), file);
    }

    @CrossOrigin
    @GetMapping("/message/{id}")
    public Message getMessage(@PathVariable String id) {
        return messageService.findMessageById(id);
    }

}
