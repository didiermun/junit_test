package com.example.junittest.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Student {
    @Id
    private Integer id;

    private String names;

    private String gender;

    private List<Course> courses;

    public Student() {
    }

    public Student(String names, String gender) {
        this.names = names;
        this.gender = gender;
    }

    public Student(Integer id, String names, String gender) {
        this.id = id;
        this.names = names;
        this.gender = gender;
    }

    public Student(Integer id, String names, String gender, List<Course> courses) {
        this.id = id;
        this.names = names;
        this.gender = gender;
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setCourse(Course courses) {
        this.courses.add(courses);
    }



    @Override
    public String toString() {
        return String.format(
                "Student [id=%s, name=%s, gender=%s, courses=%s]", id,
                names, gender, courses);
    }


}
