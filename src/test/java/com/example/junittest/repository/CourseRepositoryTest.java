package com.example.junittest.repository;

import com.example.junittest.models.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    public void findAll_success () {
        List<Course> courses = courseRepository.findAll();
        assertEquals(7, courses.size());
    }

    @Test
    public void findOneByName_Success(){

        Optional<Course> course = courseRepository.findByName("Book6");

        if (!course.isPresent())
            fail("course with this name is not found");

        assertEquals(course.get().getId(), 116);

    }



    @Test
    public void findOneByName_404(){

        Optional<Course> course = courseRepository.findByName("BOOKEE");
        assertFalse(course.isPresent());
    }


    @Test
    public void removeOne(){
        courseRepository.deleteById(111);
        List<Course> courses = courseRepository.findAll();

        assertEquals(courses.size(),6);
    }

    @Test
    public void removeOne_404(){
        courseRepository.deleteById(111);
        Optional<Course> courses = courseRepository.findById(111);

        assertEquals(false,courses.isPresent());
    }

    @Test
    public void findByCode() {
        Optional<Course> course = courseRepository.findByCode("code101");

        if (!course.isPresent())
            fail("course with this code is not found");

        assertEquals(course.get().getId(), 111);
    }

    @Test
    public void findByCode_404(){
        Optional<Course> course = courseRepository.findByCode("coddde");
        assertFalse(course.isPresent());
    }

}
