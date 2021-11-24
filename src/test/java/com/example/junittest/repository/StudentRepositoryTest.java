package com.example.junittest.repository;

import com.example.junittest.models.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findAll_success () {
        List<Student> items = studentRepository.findAll();
        assertEquals(7, items.size());
    }

    @Test
    public void findOnne_Success(){

        Optional<Student> studentOption = studentRepository.findById(101);
       if(!studentOption.isPresent())
           fail("Student with this id is not found");
       assertEquals(studentOption.get().getNames(),"Chanell");
    }

    @Test
    public void removeOne(){
        studentRepository.deleteById(101);
        List<Student> students = studentRepository.findAll();

        assertEquals(students.size(),6);
    }

    @Test
    public void findByName() {
        Optional<Student> student = studentRepository.findByNames("Cnelle");

        if (!student.isPresent())
            fail("Student with this name is not found");

        assertEquals(student.get().getId(), 107);
    }

}
