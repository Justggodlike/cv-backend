package com.epam.cv.service.impl;

import com.epam.cv.dto.MessageCreateDto;
import com.epam.cv.dto.VacancyCreateDto;
import com.epam.cv.entity.Message;
import com.epam.cv.entity.User;
import com.epam.cv.entity.Vacancy;
import com.epam.cv.repository.VacancyRepository;
import com.epam.cv.service.MessageService;
import com.epam.cv.service.UserService;
import com.epam.cv.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {


    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Vacancy getVacancyById(String id) {
        return vacancyRepository.findById(id).get();
    }

    @Override
    public Vacancy createVacancy(VacancyCreateDto vacancyCreateDto) {
        Vacancy vacancy = Vacancy.builder()
                .user(userService.getCurrentUser())
                .title(vacancyCreateDto.getTitle())
                .desc(vacancyCreateDto.getDesc())
                .salary(vacancyCreateDto.getSalary())
                .company(vacancyCreateDto.getCompany())
                .build();
        return vacancyRepository.save(vacancy);
    }

    @Override
    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }
}
