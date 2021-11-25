package com.example.junittest.controllers;

import com.example.junittest.models.Course;
import com.example.junittest.models.Student;
import com.example.junittest.services.CourseService;
import com.example.junittest.services.StudentService;
import com.example.junittest.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/get-all")
    public List<Course> getAll(){
        return courseService.getAllCourses();
    }

    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course course){
        return courseService.createCourse(course.getId(),course.getCode(), course.getName(), course.getDescription());
    }

    @PutMapping("/edit-course/{id}")
    public ResponseEntity<?> editCourse(@PathVariable int id,@RequestBody Course course ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.updateCourse(id,course));
    }

    @GetMapping("/all-courses/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") int id) {

        Course course = courseService.detailCourse(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Course not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Course removed");
    }
}
