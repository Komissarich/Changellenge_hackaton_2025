package com.example.xaxaton.service;

import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.CourseDeleteRequest;
import com.example.xaxaton.api.dto.CourseUnsubscribeRequest;
import com.example.xaxaton.api.dto.GetCoursesRequest;
import com.example.xaxaton.domain.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoursesService {
    public void createCourse(CourseCreateRequest request);
    public void deleteCourse(CourseDeleteRequest request);
    public void unsubsribeCourse(CourseUnsubscribeRequest request);
    public List<Course> getCourses(GetCoursesRequest request);
}
