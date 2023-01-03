package com.peaksoft.springboot.entities.course;

import java.util.List;

public interface CourseService {
    void saveCourse(Long id, Course course);
    void deleteCourseById(Long id);
    Course getCourseById(Long id);
    List<Course> getAllCourses(Long id);
    void updateCourse(Long id, Course course);
}
