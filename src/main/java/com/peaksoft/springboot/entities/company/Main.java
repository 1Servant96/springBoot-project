package com.peaksoft.springboot.entities.company;

import com.peaksoft.springboot.entities.group.Group;
import com.peaksoft.springboot.entities.student.Student;
import com.peaksoft.springboot.entities.course.Course;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Course course = new Course();
        Group group = new Group();
        Student student = new Student();
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();
        Student student5 = new Student();
        Student student6 = new Student();
        Student student7 = new Student();
//        List<Student> studentList = new ArrayList<>();
        group.addStudent(student);
        group.addStudent(student2);
        group.addStudent(student1);
        Group group1 = new Group();


        course.addGroup(group);
        group.addCourse(course);
//        group.setStudents(studentList);

        group1.addStudent(student3);
        group1.addStudent(student4);
        group1.addStudent(student5);
        group1.addStudent(student6);
        group1.addStudent(student7);

        course.addGroup(group1);
        group1.addCourse(course);
        company.addCourse(course);


    }

}
