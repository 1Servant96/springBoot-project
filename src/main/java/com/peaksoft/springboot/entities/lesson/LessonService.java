package com.peaksoft.springboot.entities.lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getAllLessons(Long courseId);

    void saveLesson(Long id, Lesson lesson);

    Lesson getLessonById(Long id);

    void updateLesson(Lesson lesson, Long id);

    void deleteLesson(Long id);
}
