package com.epam.cv.repository;

import com.epam.cv.entity.Message;
import com.epam.cv.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByUserTo(User userTo);

    List<Message> findByUserFrom(User userFrom);
    
}
