package com.epam.cv.dto;

import com.epam.cv.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@Builder
public class MessageDto {

    private String title;
    private String desc;

}
