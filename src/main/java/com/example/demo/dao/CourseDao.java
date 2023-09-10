package com.example.demo.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Course;

public interface CourseDao extends JpaRepository<Course,Long>{
    
    @Query(value = "SELECT * FROM course WHERE title = :courseName", nativeQuery = true)
    List<Course> findCourseByName(@Param("courseName") String title);
    
} 
