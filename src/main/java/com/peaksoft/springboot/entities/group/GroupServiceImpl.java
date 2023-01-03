package com.peaksoft.springboot.entities.group;

import com.peaksoft.springboot.entities.course.Course;
import com.peaksoft.springboot.entities.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public List<Group> getFullGroups(){
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        return courseRepository.getReferenceById(courseId).getGroups();
    }

    public void saveGroup(Group group, Long idOfCourse){
        Course course = courseRepository.getById(idOfCourse);
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
    }
    public void deleteGroupById(Long id){
        Group group = getGroupById(id);
        for (Course c: group.getCourses()) {
            c.getGroups().remove(group);
        }
        group.setCourses(null);
        groupRepository.delete(group);
    }
    public Group getGroupById(Long id){
        return groupRepository.getById(id);
    }
    @Override
    public void updateGroup(Group group, Long id) {
        Group group1 = groupRepository.findById(id).get();
        group1.setGroupName(group.getGroupName());
        group1.setId(group.getId());
        group1.setImage(group.getImage());
        groupRepository.save(group1);
    }

    @Override
    public void assignGroup(Long idCourse, Long idGroup) throws Exception {
        Group group = groupRepository.findById(idGroup).get();
        Course course = courseRepository.findById(idCourse).get();
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (Objects.equals(g.getId(), idCourse)) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        courseRepository.save(course);
        groupRepository.save(group);
    }
}