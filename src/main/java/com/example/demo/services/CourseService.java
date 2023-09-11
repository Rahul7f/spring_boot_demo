package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.Response.GlobalResponse;
import com.example.demo.entities.Course;

public interface CourseService {
    public ResponseEntity<GlobalResponse> getCourses();    
    public ResponseEntity<GlobalResponse> getCourseById(long id);
    public ResponseEntity<GlobalResponse> addCourse(Course course);
    public ResponseEntity<GlobalResponse> updateCourse(Course course);
    public ResponseEntity<GlobalResponse> deleteCourse(long  id); 
    public ResponseEntity<GlobalResponse> getCoursesByCourseName(String courseName); 
}
