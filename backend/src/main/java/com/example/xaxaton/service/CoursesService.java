package com.example.xaxaton.service;

import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.CourseDeleteRequest;
import com.example.xaxaton.api.dto.CourseUnsubscribeRequest;
import org.springframework.stereotype.Service;

@Service
public interface CoursesService {
    public void createCourse(CourseCreateRequest request);
    public void deleteCourse(CourseDeleteRequest request);
    public void unsubsribeCourse(CourseUnsubscribeRequest request);
}
