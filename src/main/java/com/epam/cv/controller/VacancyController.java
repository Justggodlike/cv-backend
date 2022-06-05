package com.epam.cv.controller;

import com.epam.cv.dto.VacancyCreateDto;
import com.epam.cv.entity.AuthBody;
import com.epam.cv.entity.Vacancy;
import com.epam.cv.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VacancyController {

    @Autowired
    VacancyService vacancyService;

    @CrossOrigin
    @GetMapping("/vacancy/all")
    public List<Vacancy> getVacancies() {
        return vacancyService.getVacancies();
    }

    @CrossOrigin
    @GetMapping("/vacancy/{id}")
    public Vacancy getVacancy(String id) {
        return vacancyService.getVacancyById(id);
    }

    @PostMapping("vacancy/add")
    public Vacancy createVacancy(@RequestBody VacancyCreateDto vacancyCreateDto) {
        return vacancyService.createVacancy(vacancyCreateDto);
    }

}
