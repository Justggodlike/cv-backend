package com.epam.cv.repository;

import com.epam.cv.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    
    User findByEmail(String email);

    User findByFullName(String fullName);
    
}
