package com.epam.cv.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Setter
@Getter
@Builder
@Document(collection = "message")
public class Message {

    @Id
    private String id;
    @DBRef
    private User userFrom;
    @DBRef
    private User userTo;
    private String title;
    private String desc;
    private String fileId;

}
