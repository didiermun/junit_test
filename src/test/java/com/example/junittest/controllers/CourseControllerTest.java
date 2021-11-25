package com.example.junittest.controllers;

import com.example.junittest.models.Course;
import com.example.junittest.services.CourseService;
import com.example.junittest.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    @MockBean
    private CourseService courseServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCourses_Success() throws Exception {
        List<Course> asList = Arrays.asList(new Course(1, "code101", "Book1","Nice Book"),
                new Course(2, "code102", "Book2","Nice Book"));

        when(courseServiceMock.getAllCourses()).thenReturn(asList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/course/get-all")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1, \"code\":\"code101\",\"name\":\"Book1\",\"description\":\"Nice Book\"} ,{\"id\":2, \"code\":\"code102\",\"name\":\"Book2\",\"description\":\"Nice Book\"} ]"))
                .andReturn();

    }

    @Test
    public void getOneCourse_Success() throws Exception {
        Course course =new Course(2, "code102", "Book2","Nice Book");
        when(courseServiceMock.detailCourse(2)).thenReturn(course);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/course/all-courses/2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":2, \"code\":\"code102\",\"name\":\"Book2\",\"description\":\"Nice Book\"}"))
                .andReturn();
    }

    @Test
    public void getOneCourse_404() throws Exception {
        Course course =new Course(2, "code102", "Book2","Nice Book");
        when(courseServiceMock.detailCourse(course.getId())).thenReturn(course);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/course/all-courses/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":false,\"message\":\"Course not found\"}"))
                .andReturn();
    }

    @Test
    public void createCourseTest() throws Exception{
        Course course = new Course(2, "code102", "Book2","Nice Book");
        when(courseServiceMock.createCourse(2, "code102", "Book2","Nice Book")).thenReturn(course);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/course/add-course")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(course));

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void updateCourseTest() throws Exception{
        when(courseServiceMock.updateCourse(anyInt(), any(Course.class))).thenReturn(new Course(2, "code102", "Book2","Nice Book")
                ,new Course(1, "code101", "Book1","Nice Book"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/course/edit-course/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{  \"code\":\"code103\",\"name\":\"Book3\",\"description\":\"Nice Bookk\"}");
        mockMvc.perform(request).andExpect(status().isAccepted()).andExpect(content().json("{\"id\":2, \"code\":\"code102\",\"name\":\"Book2\",\"description\":\"Nice Book\"}"));

    }


    @Test
    public void deleteCourseTest() throws Exception{
        Course course = new Course(1, "code101", "Book1","Nice Book");
        doNothing().when(courseServiceMock).deleteCourse(course.getId());
        mockMvc.perform(delete("/api/course/" + course.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

}
