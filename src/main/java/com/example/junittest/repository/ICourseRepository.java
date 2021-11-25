package com.example.junittest.repository;

import com.example.junittest.models.Course;
import com.example.junittest.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByCode(String code);
    Optional<Course> findByName(String name);
}
