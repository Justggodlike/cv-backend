package com.epam.cv.service;

import com.epam.cv.dto.VacancyCreateDto;
import com.epam.cv.entity.Vacancy;

import java.util.List;

public interface VacancyService {

    Vacancy findVacancyById(String Id);

    Vacancy createVacancy(VacancyCreateDto vacancyCreateDto);

    List<Vacancy> getVacancies();

}
