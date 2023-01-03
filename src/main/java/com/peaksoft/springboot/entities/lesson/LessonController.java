package com.peaksoft.springboot.entities.lesson;

import com.peaksoft.springboot.entities.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;

    @Autowired
    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping("/lessons/{id}")
    public String getAllLessons(@PathVariable Long id, Model model) {
        model.addAttribute("lessons", lessonService.getAllLessons(id));
        model.addAttribute("courses",courseService.getAllCourses(id));
        model.addAttribute("courseId",id);
        return "/lessons/lessons";
    }
    @GetMapping("/lessons/{id}/newLesson")
    public String addLesson(@PathVariable Long id, Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courseId", id);
        return "/lessons/newLesson";

    }
    @PostMapping("/{id}/saveLesson")
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson, @PathVariable Long id) {
        lessonService.saveLesson(id, lesson);
        return "redirect:/lessons/" + id;
    }
    @GetMapping("/editLesson/{id}")
    public String updateLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", lesson.getCourse().getId());
        return "/lessons/editLesson";
    }

    @PostMapping ("/{courseId}/{id}/saveUpdateLesson")
    public String saveUpdateLesson(@PathVariable("courseId") Long courseId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(lesson, id);
        return "redirect:/lessons/" + courseId;
    }
    @GetMapping("/{courseId}/{id}/deleteLesson")
    public String deleteLesson(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        lessonService.deleteLesson(id);

        return "redirect:/lessons/" + courseId;
    }

}
