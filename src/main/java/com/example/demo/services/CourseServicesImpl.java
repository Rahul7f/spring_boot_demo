package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.demo.dao.CourseDao;
import com.example.demo.entities.Course;

import jakarta.validation.Valid;

@Service
public class CourseServicesImpl implements CourseService {

    @Autowired
    private CourseDao dao;

    public CourseServicesImpl() {

    }

    @Override
    public List<Course> getCourses() {

        return dao.findAll();
    }

    @Override
    public Object getCourseById(long id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Course> courseOptional = dao.findById(id);
        if (courseOptional.isPresent()) {
            return courseOptional.get();
        } else {
            response.put("status", false);
            response.put("message", "Course not found");
            return response;
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> addCourse(@Valid Course course) {
       Map<String, Object> response = new HashMap<>();
    
        try {
            Course updatedCourse = dao.save(course);
            if (updatedCourse != null) {
                response.put("status", true);
                response.put("message", "Course added successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", false);
                response.put("message", "Course not added");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", "An error occurred while updating the course");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> updateCourse(@Valid Course course, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            response.put("status", false);
            response.put("message", "Validation failed");
            response.put("errors", bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Course updatedCourse = dao.save(course);
            if (updatedCourse != null) {
                response.put("status", true);
                response.put("message", "Course updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", false);
                response.put("message", "Course not updated");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", "An error occurred while updating the course");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public Map<String, Object> deleteCourse(long id) {
        Map<String, Object> response = new HashMap<>();
        // Check if the entity exists before attempting deletion
        Optional<Course> courseOptional = dao.findById(id);
        if (courseOptional.isPresent()) {
            Course entity = courseOptional.get();
            // Perform the deletion
            dao.delete(entity);
            // Check if the entity still exists after deletion
            if (!dao.existsById(id)) {
                response.put("status", "success");
                response.put("message", "Course with ID " + id + " deleted successfully.");
            } else {
                response.put("status", "error");
                response.put("message", "Course with ID " + id + " was not deleted.");
            }
        } else {
            response.put("status", "error");
            response.put("message", "Course with ID " + id + " not found.");
        }

        return response;
    }

    @Override
    public List<Course> getCoursesByCourseName(String courseName) {
        return dao.findCourseByName(courseName);
    }

}
