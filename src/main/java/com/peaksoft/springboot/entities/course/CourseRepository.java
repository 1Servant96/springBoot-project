package com.peaksoft.springboot.entities.course;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "select c from Course c where c.company.id = :companyId")
    List<Course> getAllCourses(Long companyId);
}
