package com.example.xaxaton.repository;

import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.domain.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
}
