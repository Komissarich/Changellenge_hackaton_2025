package com.example.xaxaton.api.controller;


import com.example.xaxaton.api.dto.*;
import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.domain.entity.Material;
import com.example.xaxaton.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CoursesController {
    @Autowired
    private CoursesService courseService;

    @CrossOrigin
    @PostMapping("/create")
    public String createCourse(@RequestBody CourseCreateRequest request) {
        System.out.println("trying to create course");
        courseService.createCourse(request);
        return "successfully created course";
    }

    @CrossOrigin
    @PostMapping("/get")
    public List<Course> getCourses(@RequestBody GetCoursesRequest request) {
        System.out.println("trying to get all courses from teacher");
        return courseService.getCourses(request);
    }
    @PostMapping("/delete")
    public String deleteCourse(@RequestBody CourseDeleteRequest request) {
        System.out.println("trying to delete course");
        courseService.deleteCourse(request);
        return "successfully deleted course";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribeCourse(@RequestBody CourseUnsubscribeRequest request) {
        System.out.println("trying to unsubscribe to course");
        courseService.unsubsribeCourse(request);
        return "successfully unsubscribed course";
    }

    @PostMapping("/findCourse")
    public Course getCourse(@RequestBody GetCourseRequest request) {
        System.out.println("trying to get one course");
        return courseService.getCourse(request);
    }

    @PostMapping("/createMaterial")
    public String createMaterial(@RequestBody CreateMaterialRequest request) {
        System.out.println("trying to create material");
        courseService.createMaterial(request);
        return "created material";
    }

    @PostMapping("/getMaterials")
    public List<Material> createMaterial(@RequestBody GetMaterialsRequest request) {
        System.out.println("trying to get materials from course");
        System.out.println(request.getCourse_id());
        return courseService.getMaterials(request);
    }
}
