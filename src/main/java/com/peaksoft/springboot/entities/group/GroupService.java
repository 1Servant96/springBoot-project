package com.peaksoft.springboot.entities.group;

import java.util.List;

public interface GroupService {
    List<Group> getFullGroups();
    public List<Group> getAllGroups(Long courseId);
    Group getGroupById(Long id);
    void deleteGroupById(Long id);
    void saveGroup(Group group, Long idOfCourse);
    void updateGroup(Group group, Long id);
    void assignGroup(Long idCourse, Long idGroup) throws Exception;

}
