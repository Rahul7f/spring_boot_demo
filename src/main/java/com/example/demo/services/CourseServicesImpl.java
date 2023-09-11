package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.demo.Response.GlobalResponse;
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
    public ResponseEntity<GlobalResponse> getCourses() {
        // return ResponseEntity
        List<Course> courses = dao.findAll();
        
        GlobalResponse globalResponse = new GlobalResponse();
        if(courses.isEmpty()){
            globalResponse.setStatus(false);
            globalResponse.setMessage("No courses found");
            return ResponseEntity.ok(globalResponse);
           
        }else{
            globalResponse.setStatus(true);
            globalResponse.setMessage("Courses found");
            globalResponse.setData(courses);
            return ResponseEntity.ok(globalResponse);
        }
        
    }

    @Override
    public ResponseEntity<GlobalResponse> getCourseById(long id) {
        GlobalResponse globalResponse = new GlobalResponse();
        Optional<Course> courseOptional = dao.findById(id);
        if (courseOptional.isPresent()) {
            globalResponse.setStatus(true);
            globalResponse.setMessage("Course found");
            globalResponse.setData(courseOptional.get());
            return ResponseEntity.ok(globalResponse); 
        } else {
            globalResponse.setStatus(false);
            globalResponse.setMessage("Course not found");
            return ResponseEntity.ok(globalResponse);
        }

    }

    @Override
    public ResponseEntity<GlobalResponse> addCourse(Course course) {
        GlobalResponse globalResponse = new GlobalResponse();
        // Check for validation errors

        try {
            Course updatedCourse = dao.save(course);
            if (updatedCourse != null) {
               globalResponse.setStatus(true);
               globalResponse.setMessage("Course added successfully");
               globalResponse.setData(course);
               return ResponseEntity.ok(globalResponse);
            } else {
                globalResponse.setStatus(false);
                globalResponse.setMessage("Course not added");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalResponse);
               
            }
        } catch (Exception e) {
            globalResponse.setStatus(false);
            globalResponse.setMessage("An error occurred while added the course");
            globalResponse.setData(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalResponse);
        
        }

    }

    @Override
    public ResponseEntity<GlobalResponse> updateCourse(Course course) {
        GlobalResponse globalResponse = new GlobalResponse();
    
        try {
            Course updatedCourse = dao.save(course);
            if (updatedCourse != null) {
               globalResponse.setStatus(true);
               globalResponse.setMessage("Course updated successfully");
               globalResponse.setData(course);
               return ResponseEntity.ok(globalResponse);
            } else {
                globalResponse.setStatus(false);
                globalResponse.setMessage("Course not updated");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalResponse);
               
            }
        } catch (Exception e) {
            globalResponse.setStatus(false);
            globalResponse.setMessage("An error occurred while updating the course");
            globalResponse.setData(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalResponse);
        
        }
    }

    @Override
    public ResponseEntity<GlobalResponse> deleteCourse(long id) {
        GlobalResponse globalResponse = new GlobalResponse();
        // Check if the entity exists before attempting deletion
        Optional<Course> courseOptional = dao.findById(id);
        if (courseOptional.isPresent()) {
            Course entity = courseOptional.get();
            // Perform the deletion
            dao.delete(entity);
            // Check if the entity still exists after deletion
            if (!dao.existsById(id)) {
                globalResponse.setStatus(true);
                globalResponse.setMessage("Course deleted successfully");
                return ResponseEntity.ok(globalResponse);
               
            } else {
                globalResponse.setStatus(false);
                globalResponse.setMessage("Course not deleted");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalResponse);
               
            }
        } else {
            globalResponse.setStatus(false);
            globalResponse.setMessage("Course not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalResponse);
           
        }
    }

    @Override
    public ResponseEntity<GlobalResponse> getCoursesByCourseName(String courseName) {
        GlobalResponse globalResponse = new GlobalResponse();
        List<Course> courses = dao.findCourseByName(courseName);
        if(courses.isEmpty()){
            globalResponse.setStatus(false);
            globalResponse.setMessage("No courses found");
            return ResponseEntity.ok(globalResponse);
        }else{
            globalResponse.setStatus(true);
            globalResponse.setMessage("Courses found");
            globalResponse.setData(courses);
            return ResponseEntity.ok(globalResponse);
        }
    
    }

}
