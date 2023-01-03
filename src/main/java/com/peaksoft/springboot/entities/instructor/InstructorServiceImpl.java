package com.peaksoft.springboot.entities.instructor;

import com.peaksoft.springboot.entities.course.Course;
import com.peaksoft.springboot.entities.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    @Override
    public List<Instructor> getInstructorList() {
        return instructorRepository.findAll();
    }

    @Override
    public List<Instructor> getAllInstructors(Long courseId) {
        return instructorRepository.getAllInstructorByCourseId(courseId);
    }

    @Override
    public void saveInstructor(Long id, Instructor instructor) {
        Course course = courseRepository.getById(id);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getById(id);
    }

    @Override
    public void updateInstructor(Instructor instructor, Long id) {
        Instructor instructor1 = instructorRepository.getById(id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructorRepository.save(instructor1);
    }

    @Override
    public void assignInstructor(Long courseId, Long instructorId) throws IOException {
        Instructor instructor = instructorRepository.getById(instructorId);
        Course course = courseRepository.getById(courseId);
        instructor.getCourse().getInstructors().remove(instructor);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        courseRepository.save(course);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteById(id);
    }
}
