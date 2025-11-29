package com.example.xaxaton.repository;

import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.domain.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Query(value = "INSERT INTO course_teachers (course_id, teacher_id) VALUES (:courseId, :teacherId) " +
            "ON CONFLICT (course_id, teacher_id) DO NOTHING", nativeQuery = true)
    void addTeacher(@Param("courseId") Long courseId, @Param("teacherId") Long teacherId);

    // Удаление привязки
    @Modifying
    @Query(value = "DELETE FROM course_teachers WHERE course_id = :courseId AND teacher_id = :teacherId", nativeQuery = true)
    void removeTeacher(@Param("courseId") Long courseId, @Param("teacherId") Long teacherId);

    // Все учителя курса
    @Query(value = "SELECT t FROM Teacher t WHERE t.id IN (SELECT ct.teacher_id FROM course_teachers ct WHERE ct.course_id = :courseId)", nativeQuery = true)
    List<Teacher> findTeachersByCourseId(@Param("courseId") Long courseId);

    // ВСЕ КУРСЫ УЧИТЕЛЯ
    @Query(value = "SELECT c FROM Course c WHERE c.id IN (SELECT ct.course_id FROM course_teachers ct WHERE ct.teacher_id = :teacherId)",nativeQuery = true)
    List<Course> findCoursesByTeacherId(@Param("teacherId") Long teacherId);
}
