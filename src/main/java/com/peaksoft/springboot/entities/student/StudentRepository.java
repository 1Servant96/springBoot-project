package com.peaksoft.springboot.entities.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select s from Student s where s.group.id = :groupId")
    List<Student> getAllStudentByGroupId(Long groupId);
    }

