package com.example.junittest.repository;

import com.example.junittest.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Integer> {

}
