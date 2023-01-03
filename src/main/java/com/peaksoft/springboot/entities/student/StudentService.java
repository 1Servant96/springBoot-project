package com.peaksoft.springboot.entities.student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    public List<Student> getStudentList();

    void saveStudent(Student student, Long id) throws Exception;
    void deleteStudentById(Long id);
    Student getStudentById(Long id);
    List<Student> getAllStudents(Long idOfGroup);
    void updateStudent(Student student, Long id);
    public void assignStudent(Long groupId, Long studentId) throws IOException;
    void addStudentToGroup(Student student, Long id);


}
