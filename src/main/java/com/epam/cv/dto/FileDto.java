package com.epam.cv.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileDto {

    private byte[] bytes;
    private String name;

}
