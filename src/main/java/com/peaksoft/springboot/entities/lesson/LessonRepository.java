package com.peaksoft.springboot.entities.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query(value = "select l from Lesson l where l.course.id = :courseId")
    List<Lesson> getAllLessonByCourseId(Long courseId);
}
