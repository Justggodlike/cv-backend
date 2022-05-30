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
@Document(collection = "vacancy")
public class Vacancy {

    @Id
    private String id;
    private String title;
    private String desc;
    private String salary;
    private String company;
    @DBRef
    private User user;

}
