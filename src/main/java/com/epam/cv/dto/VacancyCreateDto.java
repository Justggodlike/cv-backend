package com.epam.cv.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VacancyCreateDto {

    private String title;
    private String desc;
    private String salary;
    private String company;

}
