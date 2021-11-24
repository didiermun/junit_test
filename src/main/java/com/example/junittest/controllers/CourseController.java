package com.example.junittest.controllers;

import com.example.junittest.models.Course;
import com.example.junittest.models.Student;
import com.example.junittest.services.CourseService;
import com.example.junittest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/get-all")
    public List<Course> getAll(){
        return courseService.getAllCourses();
    }

    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course course){
        return courseService.createCourse(course.getCode(), course.getName(), course.getDescription(), course.getId());
    }

    @PostMapping("/edit-course/{courseId}")
    public Course editCourse(@RequestBody Course course, @PathVariable Integer courseId){
        return courseService.updateCourse(courseId, course);
    }
}
