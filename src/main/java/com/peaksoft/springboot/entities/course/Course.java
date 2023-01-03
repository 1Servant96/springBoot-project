package com.peaksoft.springboot.entities.course;

import com.peaksoft.springboot.entities.company.Company;
import com.peaksoft.springboot.entities.group.Group;
import com.peaksoft.springboot.entities.instructor.Instructor;
import com.peaksoft.springboot.entities.lesson.Lesson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@Table (name = "courses")


public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "course's name should not be empty")
    @Size(min = 2, max = 50, message = "name should be between 2 and 50 characters")
    @Column
    private String courseName;
    @Column
    private int duration;
    @NotEmpty(message = "Course description cant be null")
    @Column
    private String description;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST, REMOVE}, fetch = FetchType.EAGER)
    private List<Group> groups = new ArrayList<>();

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Instructor> instructors;

    @OneToMany
    private List<Lesson> lessons;
    public void addGroup(Group group){
        groups.add(group);
    }
    public void addInstructor(Instructor instructor){
        if (instructors==null){
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
    }
    public void addLesson(Lesson lesson){
        if (lessons==null){
            lessons=new ArrayList<>();
        }
        lessons.add(lesson);
    }
}
