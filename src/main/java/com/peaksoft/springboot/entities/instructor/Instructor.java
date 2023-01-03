package com.peaksoft.springboot.entities.instructor;

import com.peaksoft.springboot.entities.group.Group;
import com.peaksoft.springboot.entities.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
//@Table (name = "instructors")

@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the first name couldn't be empty")
    @Size(min = 2, message = "the first name couldn't be less than 2 letters")
    private String firstName;
    @NotEmpty(message = "the last name couldn't be empty")
    @Size(min = 2, message = "the last name couldn't be less than 2 letters")
    private String lastName;
    @NotEmpty(message = "the phone number name couldn't be empty")
    @Size(min = 2, message = "the phone number couldn't be less than 2 letters")
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    private String email;
    @NotEmpty(message = "the specialization couldn't be empty")
    @Size(min = 2, message = "the specialization couldn't be less than 2 letters")
    private String specialization;
    @ManyToOne(cascade = {CascadeType.MERGE, DETACH, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    private Course course;

    public void addInstructorToCourse(Instructor instructor) {
        if (course.getInstructors().stream().noneMatch(x -> Objects.equals(x.id, instructor.id))) {
            course.getInstructors().add(instructor);
        }
        ;
    }

    public int countAmountOfStudentsInstructor(){
        int sum = 0;
        for (Group group:course.getGroups()) {
           sum = sum + group.getStudents().size();
        }
        return sum;
    }
}
