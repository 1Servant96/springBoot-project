package com.peaksoft.springboot.entities.course;

import com.peaksoft.springboot.entities.company.Company;
import com.peaksoft.springboot.entities.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public List<Course> getAllCourses(Long id) {
        return courseRepository.getAllCourses(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = courseRepository.getById(id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        courseRepository.save(course1);
    }

    @Override
    public void saveCourse(Long id, Course course) {
        Company company = companyRepository.getById(id);
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(Long id) {
    courseRepository.deleteById(id);
    }

    public Course getCourseById(Long id) {
        return courseRepository.getById(id);
    }
}
