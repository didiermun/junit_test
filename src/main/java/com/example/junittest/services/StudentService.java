package com.example.junittest.services;

import com.example.junittest.models.Student;
import com.example.junittest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent( Integer id,String name, String gender){
        Student student = new Student(id, name, gender);

        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id){
        studentRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Student not found with id"+ id));
         studentRepository.deleteById(id);
    }

    public Student updateStudent(Integer id, Student student){
        studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found with id"+ id));

        student.setId(id);

        return studentRepository.save(student);

    }

    public Student detailStudent(Integer id){
        return studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student with id "+id+ " not found!"));
    }


}
