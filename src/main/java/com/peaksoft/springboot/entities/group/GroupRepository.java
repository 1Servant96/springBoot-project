package com.peaksoft.springboot.entities.group;

import com.peaksoft.springboot.entities.instructor.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
