package com.example.xaxaton.service.impl;

import com.example.xaxaton.api.dto.LoginTeacherRequest;
import com.example.xaxaton.api.dto.TeacherRegisterRequest;
import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.domain.entity.Teacher;
import com.example.xaxaton.repository.CoursesRepository;
import com.example.xaxaton.repository.TeacherRepository;
import com.example.xaxaton.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void RegisterTeacher(TeacherRegisterRequest request) {
        Teacher teacher = new Teacher();
        teacher.setEmail(request.getEmail());
        teacher.setName(request.getName());
        teacher.setPassword(request.getPassword());
        System.out.println("new_teacher");
        System.out.println(teacher);
        Teacher saved = teacherRepository.save(teacher);
        System.out.println("created");
        System.out.println(saved);
    }

        @Override
        public boolean LoginTeacher(LoginTeacherRequest request) {
            Optional<Teacher> teacherOpt = teacherRepository.findByEmail(request.getEmail());

            if (teacherOpt.isEmpty()) {
                System.out.println("учитель не найден");
                return false;
            }
            else if (teacherOpt.)
            else {
                return true;
            }
        }
}
