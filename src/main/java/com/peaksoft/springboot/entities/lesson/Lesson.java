package com.peaksoft.springboot.entities.lesson;

import com.peaksoft.springboot.entities.task.Task;
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
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the lesson name couldn't be empty")
    @Size(min = 2, message = "the lesson name shouldn't be less than 2")
    private String lessonName;
    @OneToOne
    private Course course;
    @OneToMany
    private List<Task> tasks = new ArrayList<>();


    public void addTaskToLesson(Task task){
        if(tasks.stream().noneMatch(x -> Objects.equals(x.getId(), task.getId()))){
        tasks.add(task);
        }
    }
}
