package com.peaksoft.springboot.entities.company;

import com.peaksoft.springboot.entities.group.Group;
import com.peaksoft.springboot.entities.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the company name couldn't be empty")
    @Size(min = 2, message = "the company name couldn't be less than 2 letters")
    @Column
    private String companyName;
    @Column
    @NotEmpty(message = "the company name couldn't be empty")
    @Size(min = 2, message = "the company name couldn't be less than 2 letters")
    private String locatedCountry;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Course> courses = new ArrayList<>();

    private int numberOfStudents;
    public void plusStudent(){
        numberOfStudents++;
    }
    public void minusStudent(){
        numberOfStudents--;
    }


    public void addCourse(Course course) {
//        if(checkCourse(course)){
        courses.add(course);
//    }
    }
    public boolean checkCourse(Course course){
       return courses.stream().noneMatch(x-> Objects.equals(x.getId(), course.getId()));
    }

//    public int countAmountOfStudentsInCompany(){
//        int sum = 0;
//        for (Course course:courses) {
//            for (Group group:course.getGroups()) {
//                sum = sum+group.getStudents().size();
//            }
//        }
//        return sum;
//    }
}
