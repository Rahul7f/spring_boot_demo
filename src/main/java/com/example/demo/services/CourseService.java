package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.example.demo.entities.Course;

public interface CourseService {
    public List<Course> getCourses();    
    public Object getCourseById(long id);
    public ResponseEntity<Map<String, Object>> addCourse(Course course);
    public ResponseEntity<Map<String, Object>> updateCourse(Course course,BindingResult bindingResult);
    public Map<String,Object> deleteCourse(long  id); 
    public List<Course> getCoursesByCourseName(String courseName); 
}
