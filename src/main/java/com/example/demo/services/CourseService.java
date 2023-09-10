package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.Course;

public interface CourseService {
    public List<Course> getCourses();    
    public Object getCourseById(long id);
    public Map<String, Object> addCourse(Course course);
    public Map<String, Object> updateCourse(Course course);
    public Map<String,Object> deleteCourse(long  id); 
    public List<Course> getCoursesByCourseName(String courseName); 
}
