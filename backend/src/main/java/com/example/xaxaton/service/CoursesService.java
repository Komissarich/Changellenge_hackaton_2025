package com.example.xaxaton.service;

import com.example.xaxaton.api.dto.*;
import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.domain.entity.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoursesService {
    public void createCourse(CourseCreateRequest request);
    public void deleteCourse(CourseDeleteRequest request);
    public void unsubsribeCourse(CourseUnsubscribeRequest request);
    public List<Course> getCourses(GetCoursesRequest request);
    public Course getCourse(GetCourseRequest request);
    public void createMaterial(CreateMaterialRequest request);
    public List<Material> getMaterials(GetMaterialsRequest request);
}
