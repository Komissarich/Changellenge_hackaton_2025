package com.example.xaxaton.repository;

import com.example.xaxaton.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Course, Long> {

    @Query("SELECT teacher_id FROM course_teachers WHERE course_id = :courseId")
    List<Long> findTeacherIdsByCourseId(@Param("courseId") Long courseId);

    @Query(value = "INSERT INTO course_teachers (course_id, teacher_id) VALUES (:courseId, :teacherId)", nativeQuery = true)
    @Modifying
    void addTeacher(@Param("courseId") Long courseId, @Param("teacherId") Long teacherId);
}
