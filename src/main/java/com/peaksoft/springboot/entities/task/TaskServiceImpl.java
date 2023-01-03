package com.peaksoft.springboot.entities.task;

import com.peaksoft.springboot.entities.course.Course;
import com.peaksoft.springboot.entities.lesson.Lesson;
import com.peaksoft.springboot.entities.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<Task> getAllTasks(Long lessonId) {
        return taskRepository.getAllTaskByLessonId(lessonId);
    }

    @Override
    public void addTask(Long id, Task task) {
        Lesson lesson = lessonRepository.getById(id);
        lesson.addTaskToLesson(task);
        task.setLesson(lesson);
        taskRepository.save(task);

    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getById(id);
    }

    @Override
    public void updateTask(Task task, Long id) {
        Task task1 = taskRepository.findById(id).get();
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        taskRepository.save(task1);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);

    }
}
