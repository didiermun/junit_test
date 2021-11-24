package com.example.junittest.controllers;
import com.example.junittest.models.Student;
import com.example.junittest.services.StudentService;
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
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentService studentServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllStudents_Success() throws Exception {
        List<Student> asList = Arrays.asList(new Student(1, "Chanelle", "female"),
                new Student(2, "Hirwa", "male"));

        when(studentServiceMock.getAllStudents()).thenReturn(asList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/student/get-all")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1, \"names\":\"Chanelle\",\"gender\":\"female\"} ,{\"id\":2, \"names\":\"Hirwa\",\"gender\":\"male\"} ]"))
                .andReturn();

    }
    @Test
   public void getOneStudent_Success() throws Exception {
        Student student = new Student(1,"Irakoze", "other");
        when(studentServiceMock.detailStudent(1)).thenReturn(student);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/student/all-students/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1, \"names\":\"Irakoze\",\"gender\":\"other\"}"))
                .andReturn();
    }
    @Test
    public void getOneStudent_404() throws Exception {

        Student student = new Student(1,"Irakoze", "other");
        when(studentServiceMock.detailStudent(student.getId())).thenReturn(student);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/student/all-students/2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":false,\"message\":\"Student not found\"}"))
                .andReturn();
    }

    @Test
    public void createStudentTest() throws Exception{
        Student student = new Student(1, "Chanelle", "female");
       when(studentServiceMock.createStudent(1,"Chanelle","female")).thenReturn(student);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/student/add-student")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(student));

       MvcResult result = mockMvc
               .perform(request)
               .andExpect(status().isCreated())
               .andReturn();
    }

    @Test
    public void updateStudentTest() throws Exception{
        when(studentServiceMock.updateStudent(anyInt(), any(Student.class))).thenReturn(new Student(2,"Lorenzo","male")
        ,new Student(3,"Edisor","other"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/student/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"names\":\"Chanelle\",\"gender\":\"female\"}");
        mockMvc.perform(request).andExpect(status().isAccepted()).andExpect(content().json("{\"id\":2, \"names\":\"Lorenzo\",\"gender\":\"male\"}"));

    }

    @Test
    public void deleteStudentTest() throws Exception{
        Student student = new Student(3,"Edisor","other");
        doNothing().when(studentServiceMock).deleteStudent(student.getId());
        mockMvc.perform(delete("/api/student/" + student.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

}



