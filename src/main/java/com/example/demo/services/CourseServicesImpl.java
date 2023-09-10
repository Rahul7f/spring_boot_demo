package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseDao;
import com.example.demo.entities.Course;

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
        if(courseOptional.isPresent()){
            return courseOptional.get();
        }else{
            response.put("status", false);
            response.put("message", "Course not found");
            return response;
        }


    }

    @Override
    public Map<String, Object> addCourse(Course course) {
        System.out.println("addCourse: " + course);
        // return dao.save(course);
        try {
            Map<String, Object> response = new HashMap<>();
            Course entry = dao.save(course);
            if (entry == null) {
                response.put("status", false);
                response.put("message", "Course not added");
                return response;
            } else {
                response.put("status", true);
                response.put("message", "Course added");
                return response;
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", false);
            response.put("message", e.toString());
            return response;

        }

    }

    @Override
    public Map<String, Object> updateCourse(Course course) {
       System.out.println("addCourse: " + course);
        // return dao.save(course);
        try {
            Map<String, Object> response = new HashMap<>();
            Course entry = dao.save(course);
            if (entry == null) {
                response.put("status", false);
                response.put("message", "Course not updated");
                return response;
            } else {
                response.put("status", true);
                response.put("message", "Course updated successfully ");
                return response;
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", false);
            response.put("message", e.toString());
            return response;

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
