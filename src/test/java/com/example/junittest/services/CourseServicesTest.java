package com.example.junittest.services;

import com.example.junittest.models.Course;
import com.example.junittest.models.Student;
import com.example.junittest.repository.ICourseRepository;
import com.example.junittest.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServicesTest {

    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    CourseService courseService;


    @Test
    public void returnCourses(){
        when(courseRepository.findAll()).thenReturn(Arrays.asList(
                new Course(1, "M002","Mathematics","Mathematics Course"),
                new Course(2,"P001","Physics", "Physics Course"),
                new Course(3,"E002", "English","English Course")));
        assertEquals("P001",courseService.getAllCourses().get(1).getCode());
    }

    @Test
    public  void createCourse(){
        when(courseRepository.save(ArgumentMatchers.any(Course.class))).thenReturn(new Course(1,"SE01","Software Engineering","Software engineering"));
        assertEquals("SE01",courseService.createCourse( 1,"M001","Math 1","Mathematics I").getCode());
//        assertEquals("M001",courseService.createCourse("M001","Math 1","Mathematics I", 1).getCode());
    }

    @Test
    public void deleteCourse(){
        Course cr = new Course(5, "M002","Mathematics","Mathematics Course");
        when(courseRepository.findById(cr.getId())).thenReturn(Optional.of(cr));

        courseService.deleteCourse(cr.getId());

        verify(courseRepository).deleteById(cr.getId());

    }


    @Test
    public void updateCourse(){
        Course cr = new Course(6, "P002","Phy II","Physics II");
        Course newCr = new Course("E003","English III", "Advanced English");
        given(courseRepository.findById(cr.getId())).willReturn(Optional.of(cr));

        courseService.updateCourse(cr.getId(),newCr);
        verify(courseRepository).save(newCr);
        verify(courseRepository).findById(cr.getId());
    }

    @Test
    public void returnCourse(){
        Course course = new Course();
        course.setId(2);

        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        Course expected = courseService.detailCourse(course.getId());

        assertThat(expected).isSameAs(course);

        verify(courseRepository).findById(course.getId());
    }

}