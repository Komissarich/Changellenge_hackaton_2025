package com.example.xaxaton.api.controller;

import com.example.xaxaton.api.dto.CourseCreateRequest;
import com.example.xaxaton.api.dto.LoginTeacherRequest;
import com.example.xaxaton.api.dto.TeacherRegisterRequest;
import com.example.xaxaton.service.CoursesService;
import com.example.xaxaton.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @CrossOrigin
    @PostMapping("/register")
    public String registerTeacher(@RequestBody TeacherRegisterRequest request) {
        System.out.println("trying to register teacher");
        teacherService.RegisterTeacher(request);
        return "registered";
    }

    @CrossOrigin
    @PostMapping("/login")
    public String createCourse(@RequestBody LoginTeacherRequest request) {
        System.out.println("trying to login teacher");
        if (teacherService.LoginTeacher(request)) {
            return "login";
        }
        else {
            return "not found";
        }

    }
}
