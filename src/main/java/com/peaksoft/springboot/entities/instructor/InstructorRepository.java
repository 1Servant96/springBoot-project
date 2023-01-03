package com.peaksoft.springboot.entities.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query(value = "select i from Instructor i where i.course.id = :courseId")
    List<Instructor> getAllInstructorByCourseId(Long courseId);
}
