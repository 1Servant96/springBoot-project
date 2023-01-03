package com.peaksoft.springboot.entities.instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    List<Instructor> getInstructorList();
    List<Instructor> getAllInstructors(Long courseId);

    void saveInstructor(Long id,Instructor instructor);

    Instructor getInstructorById(Long id);

    void updateInstructor(Instructor instructor, Long id);
    void assignInstructor(Long courseId, Long instructorId) throws IOException;

    void deleteInstructorById(Long id);
}
