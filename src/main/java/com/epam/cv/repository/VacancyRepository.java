package com.epam.cv.repository;

import com.epam.cv.entity.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VacancyRepository extends MongoRepository<Vacancy, String> {

    Optional<Vacancy> findById(String id);
    
}
