package com.peaksoft.springboot.entities.lesson;

import com.peaksoft.springboot.entities.course.Course;
import com.peaksoft.springboot.entities.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return lessonRepository.getAllLessonByCourseId(courseId);
    }

    @Override
    public void saveLesson(Long id, Lesson lesson) {
        Course course = courseRepository.getById(id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getById(id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        Lesson lesson1 = lessonRepository.findById(id).get();
        lesson1.setLessonName(lesson.getLessonName());
        lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
