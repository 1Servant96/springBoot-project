package com.peaksoft.springboot.entities.task;

import com.peaksoft.springboot.entities.lesson.Lesson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen", sequenceName = "task_gen", allocationSize = 1)
    private Long id;

    @Column
    private String taskName;
    @Column
    private String taskText;
    @Column
    private String deadline;

    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = FetchType.EAGER)
    private Lesson lesson;
    public void indicator(Lesson lesson){
        this.lesson = lesson;
    }
}





