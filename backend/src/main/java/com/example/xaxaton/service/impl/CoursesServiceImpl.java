package com.example.xaxaton.service.impl;

import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.CourseDeleteRequest;
import com.example.xaxaton.api.dto.CourseUnsubscribeRequest;
import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.repository.CoursesRepository;
import com.example.xaxaton.service.CoursesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional

public class CoursesServiceImpl implements CoursesService {
    private final CoursesRepository courseRepository;


    // Явный конструктор — IDE сразу видит, что нужно внедрить
    public CoursesServiceImpl(
            CoursesRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void createCourse(CourseCreateRequest request) {

        Course course = new Course();
        course.setDescription(request.getDescription());
        course.setDifficulty(request.getDifficulty());
        course.setName(request.getName());
        course.setDuration(request.getDuration());
        course.setStartDate(request.getStartDate());
        course.setSchedule(request.getSchedule());


        Course saved = courseRepository.save(course);
        System.out.println(saved);

    }

    @Override
    public void deleteCourse(CourseDeleteRequest request) {

    }

    @Override
    public void unsubsribeCourse(CourseUnsubscribeRequest request) {

    }
}
