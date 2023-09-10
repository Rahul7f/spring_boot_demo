package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Course;
import com.example.demo.services.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyController {
    @Autowired
    CourseService service;

    @GetMapping("/")
    public String hello() {
        return "Server is running";
    }

    @GetMapping("/courses")
    public Object getAllCourse() {
        return service.getCourses();
    }

    @PostMapping("/courses")
    public Map<String, Object> addCourse(@RequestBody Course entity) {
        return service.addCourse(entity);
    }

    @PutMapping("/courses")
    public Map<String, Object> updateCourse(@RequestBody Course entity) {
        return service.updateCourse(entity);
    }

    @GetMapping("/courses/{id}")
    public Object getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @GetMapping("/courses/search")
    public List<Course> getCoursesByTitle(@RequestParam(name = "title") String title) {
        return service.getCoursesByCourseName(title);
    }

    @DeleteMapping("/courses/{id}")
    public Object deleteCourseById(@PathVariable Long id) {
        return service.deleteCourse(id);
    }

}
