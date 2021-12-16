package com.example.junittest.services;

import com.example.junittest.models.Course;
import com.example.junittest.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    ICourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course createCourse( Integer id,String code, String name, String description){
        Course course = new Course(id, code, name, description);

        return courseRepository.save(course);
    }

    public void deleteCourse(Integer id){
        courseRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Course not found with id"+ id));
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Integer id, Course course){
        courseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Course not found with id"+ id));

        course.setId(id);

        return courseRepository.save(course);

    }

    public Course detailCourse(Integer id){
        Optional<Course> course = courseRepository.findById(id);
             if(course.isPresent()){
                 Course course1 = course.get();
                 return course1;
             }
             return null;
    }

}
