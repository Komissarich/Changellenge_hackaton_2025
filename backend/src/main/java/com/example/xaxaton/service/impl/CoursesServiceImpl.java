package com.example.xaxaton.service.impl;

import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.CourseDeleteRequest;
import com.example.xaxaton.api.dto.CourseUnsubscribeRequest;
import com.example.xaxaton.api.dto.GetCoursesRequest;
import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.repository.CoursesRepository;
import com.example.xaxaton.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public void createCourse(CourseCreateRequest request) {

        Course course = new Course();
        course.setDescription(request.getDescription());
        course.setDifficulty(request.getDifficulty());
        course.setName(request.getName());
        course.setDuration(request.getDuration());
        course.setSchedule(request.getSchedule());
        course.setAuthor_id(request.getAuthor_id());
        course.setCover_link(request.getCover_link());
        System.out.println("new_course");
        System.out.println(course);
        Course saved = coursesRepository.save(course);
        System.out.println("new_course");
        System.out.println("created");
        System.out.println(saved);
        System.out.println("id's");
        System.out.println(saved.getId());
        System.out.println(request.getAuthor_id());
        coursesRepository.addTeacher(saved.getId(), request.getAuthor_id());
    }

    @Override
    public void deleteCourse(CourseDeleteRequest request) {

    }

    @Override
    public void unsubsribeCourse(CourseUnsubscribeRequest request) {

    }

    @Override
    public List<Course> getCourses(GetCoursesRequest request) {
        return coursesRepository.findCoursesByTeacherId(request.getTeacher_id());
    }
}
