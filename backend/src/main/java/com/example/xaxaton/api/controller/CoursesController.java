package com.example.xaxaton.api.controller;


import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.CourseDeleteRequest;
import com.example.xaxaton.api.dto.CourseUnsubscribeRequest;
import com.example.xaxaton.service.CoursesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private CoursesService courseService;

    @PostMapping("/create")
    public String createCourse(@RequestBody CourseCreateRequest request) {
        courseService.createCourse(request);
        return "successfully created";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestBody CourseDeleteRequest request) {
        courseService.deleteCourse(request);
        return "successfully deleted";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribeCourse(@RequestBody CourseUnsubscribeRequest request) {
        courseService.unsubsribeCourse(request);
        return "successfully unsubscribed";
    }


}
