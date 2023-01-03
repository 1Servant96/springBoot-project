package com.peaksoft.springboot.entities.group;

import com.peaksoft.springboot.entities.course.Course;
import com.peaksoft.springboot.entities.student.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the group name couldn't be empty")
    @Size(min = 2, message = "the group name couldn't be less than 2 letters")
    private String groupName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;
    private String image;

    @ManyToMany(cascade = {DETACH,MERGE,REFRESH,REMOVE},fetch = FetchType.LAZY, mappedBy = "groups")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(cascade = ALL, mappedBy = "group")
    private List<Student> students = new ArrayList<>();
    public void addCourse(Course course){
     courses.add(course);
    }
    public void addStudent(Student student){
        for (Course c:courses) {
            c.getCompany().plusStudent();
        }
        students.add(student);
    }
}
