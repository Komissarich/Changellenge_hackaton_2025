package com.example.xaxaton.service;

import com.example.xaxaton.api.dto.LoginTeacherRequest;
import com.example.xaxaton.api.dto.TeacherRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    public void RegisterTeacher(TeacherRegisterRequest request);
    public boolean LoginTeacher(LoginTeacherRequest request);
}
