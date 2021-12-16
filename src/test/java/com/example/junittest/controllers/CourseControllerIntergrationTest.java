package com.example.junittest.controllers;

import com.example.junittest.models.Course;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.junittest.utils.APIResponse;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CourseControllerIntergrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_Success() throws JSONException {
        String response = this.restTemplate.getForObject("/api/course/get-all",String.class);
       JSONAssert.assertEquals("[{id:111},{id:112},{id:113},{id:114},{id:115},{id:116},{id:117}]",response,false);
    }

    @Test
    public void getByID(){
        ResponseEntity<Course> response =  this.restTemplate.getForEntity("/api/course/all-courses/111",Course.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("code101", response.getBody().getCode());
        assertEquals("Book1", response.getBody().getName());
    }

    @Test
    public void getByID_404(){
        ResponseEntity<APIResponse> response =  this.restTemplate.getForEntity("/api/course/all-courses/119",APIResponse.class);
        assertEquals(404,response.getStatusCodeValue());
        assertEquals("Course not found", response.getBody().getMessage());
    }

    @Test
    public void create_success(){
        Course course = new Course(112, "code102", "Book2","Nice Book");
        ResponseEntity<Course> response = this.restTemplate.postForEntity("/api/course/add-course", course, Course.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("code102", response.getBody().getCode());
        assertEquals("Book2", response.getBody().getName());
    }

    


}
