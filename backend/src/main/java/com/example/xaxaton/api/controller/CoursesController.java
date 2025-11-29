package com.example.xaxaton.api.controller;


import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.CourseDeleteRequest;
import com.example.xaxaton.api.dto.CourseUnsubscribeRequest;
import com.example.xaxaton.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:9001", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS})
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
}
