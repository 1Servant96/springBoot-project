package com.peaksoft.springboot.entities.student;

import com.peaksoft.springboot.entities.course.Course;
import com.peaksoft.springboot.entities.group.Group;
import com.peaksoft.springboot.entities.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;


    public void deleteStudentById(Long id){
        Student student = studentRepository.getById(id);
        for (Course c:student.getGroup().getCourses()) {
            c.getCompany().minusStudent();
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public List<Student> getAllStudents(Long idOfGroup) {
        return studentRepository.getAllStudentByGroupId(idOfGroup);
    }

//    @Override
//    public void addStudentToGroup(Student student, Long idOfGroup) throws Exception {
//        List<Student> students = studentRepository.findAll();
//        for (Student i : students) {
//            if (i.getEmail().equals(student.getEmail())) {
//                throw new IOException("Student with email already exists!");
//            }
//        }
//
//        Group group = groupRepository.getById(idOfGroup);
//        group.addStudent(student);
//        student.setGroup(group);
//
//
//        studentRepository.save(student);
//    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student, Long idOfGroup) throws Exception{
        List<Student> students = studentRepository.findAll();
        for (Student i : students) {
            if (i.getEmail().equals(student.getEmail())) {
                throw new IOException("Student with this email already exists!");
            }
        }
        Group group = groupRepository.getById(idOfGroup);
        group.addStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
    }
    public void addStudentToGroup(Student student, Long id) {
        Group group = groupRepository.findById(id).get();
        student.setGroup(group);
        student.addStudentToGroup(student);
        studentRepository.save(student);
    }
    public void updateStudent(Student student, Long id){
        Student student1 = studentRepository.getById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        Student student = studentRepository.getById(studentId);
        Group group = groupRepository.getById(groupId);

        if (group.getStudents()!=null){
            for (Student g : group.getStudents()) {
                if (Objects.equals(g.getId(), studentId)) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        student.getGroup().getStudents().remove(student);
        group.addStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
        groupRepository.save(group);
    }
}
