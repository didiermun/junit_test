package com.example.junittest.controllers;


import com.example.junittest.models.Student;
import com.example.junittest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.junittest.utils.APIResponse;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/get-all")
    public List<Student> getAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("/all-students/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") int id) {

        Student student = studentService.detailStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Student not found"));
    }

    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent( student.getId(),student.getNames(), student.getGender()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id,@RequestBody Student student ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentService.updateStudent(id,student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Student removed");
    }

}
