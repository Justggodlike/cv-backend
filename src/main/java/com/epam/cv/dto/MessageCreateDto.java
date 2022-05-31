package com.epam.cv.dto;

import com.epam.cv.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageCreateDto {

    private User userTo;
    private String title;
    private String desc;
    private String filename;

}
