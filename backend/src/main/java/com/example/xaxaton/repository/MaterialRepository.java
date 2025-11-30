package com.example.xaxaton.repository;

import com.example.xaxaton.domain.entity.Course;
import com.example.xaxaton.domain.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query(value = "SELECT m.* FROM Materials m WHERE m.course_id = :courseId", nativeQuery = true)
    List<Material> findMaterialsByCourseId(@Param("courseId") Long courseId);
}
