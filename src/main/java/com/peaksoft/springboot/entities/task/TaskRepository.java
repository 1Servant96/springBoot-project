package com.peaksoft.springboot.entities.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select t from Task t where t.lesson.id = :lessonId")
    List<Task> getAllTaskByLessonId(Long lessonId);

}
