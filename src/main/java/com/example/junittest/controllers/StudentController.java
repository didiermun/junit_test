package com.example.junittest.controllers;


import com.example.junittest.models.Course;
import com.example.junittest.models.Student;
import com.example.junittest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/get-all")
    public List<Student> getAll(){
        return studentService.getAllStudents();
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student){
        return studentService.createStudent(student.getNames(), student.getGender(), student.getId());
    }

    @GetMapping("/{studentId}/courses")
    public List<Course> retrieveCoursesForStudent(@PathVariable Integer studentId) {
        return studentService.retrieveCourses(studentId);
    }

    @GetMapping("/{studentId}/courses/{courseId}")
    public Course retrieveDetailsForCourse(@PathVariable Integer studentId,
                                           @PathVariable Integer courseId) {
        return studentService.retrieveCourse(studentId, courseId);
    }

    @PostMapping("/students/{studentId}/courses")
    public Course addCourseForStudent(
            @PathVariable Integer studentId, @RequestBody Course newCourse) {

        return studentService.addCourse(studentId, newCourse);
    }


}
