package com.peaksoft.springboot.entities.student;

import com.peaksoft.springboot.entities.group.Group;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.peaksoft.springboot.enums.StudyFormat;

import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor


public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the first name couldn't be empty")
    @Size(min = 2, message = "the first name shouldn't be less than 2")
    private String firstName;
    @NotEmpty(message = "the last name couldn't be empty")
    @Size(min = 2, message = "the last name shouldn't be less than 2")
    private String lastName;
    @NotEmpty(message = "the phone number name couldn't be empty")
    @Size(min = 2, message = "the phone number couldn't be less than 2 letters")
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    private String email;
    private StudyFormat studyFormat;
    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = FetchType.EAGER)
    private Group group;

    public Student(Long id, String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }

    public void addStudentToGroup(Student student){
        if(group.getStudents().stream().noneMatch(x -> Objects.equals(x.id, student.id))){
            group.getStudents().add(student);
            student.setGroup(group);
        };
    }


}
