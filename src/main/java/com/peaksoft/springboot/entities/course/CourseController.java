package com.peaksoft.springboot.entities.course;

import com.peaksoft.springboot.entities.group.Group;
import com.peaksoft.springboot.entities.group.GroupService;
import com.peaksoft.springboot.entities.instructor.Instructor;
import com.peaksoft.springboot.entities.instructor.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class CourseController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final InstructorService instructorService;

    @Autowired
    public CourseController(GroupService groupService, CourseService courseService, InstructorService instructorService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @GetMapping("/courses/{id}")
    public String getAllCourses(@PathVariable("id") Long id,Model model,
                                @ModelAttribute("group") Group group,
                                @ModelAttribute("instructor") Instructor instructor) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("groups", groupService.getFullGroups());
        model.addAttribute("instructors", instructorService.getInstructorList());
        model.addAttribute("companyId", id);
        return "/courses/courses";
    }
    @GetMapping("/courses/{id}/newCourse")
    public String newCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "/courses/newCourse";
    }

    @PostMapping("/{id}/saveCourse")
    public String create(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "/courses/newCourse";
        }
        courseService.saveCourse(id, course);
        return "redirect:/courses/" + id;
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "courses/editCourse";
    }
    @GetMapping("{companyId}/{id}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("companyId") Long companyId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("course") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses/" + companyId;
    }
    @PostMapping("{courseId}/assignGroup")
    private String assignGroup(@PathVariable("courseId") Long courseId,
                               @ModelAttribute("group") Group group) throws Exception {
        Long id = group.getId();
        groupService.assignGroup(courseId, id);
        return "redirect:/groups/" + courseId;
    }

    @PostMapping("{courseId}/assignInstructor")
    private String assignInstructor(@PathVariable("courseId") Long courseId,
                                    @ModelAttribute("instructor") Instructor instructor) throws IOException {
        Long id = instructor.getId();
        instructorService.assignInstructor(courseId, id);
        return "redirect:/instructors/" + courseId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourse")
    public String deleteCourse(@PathVariable("id") Long id, @PathVariable("companyId") Long companyId) {
        courseService.deleteCourseById(id);
        return "redirect:/courses/" + companyId;
    }
}

