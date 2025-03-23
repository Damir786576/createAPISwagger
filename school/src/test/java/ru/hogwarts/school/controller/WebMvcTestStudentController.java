package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.services.StudentService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class WebMvcTestStudentController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent() throws Exception {
        when(studentService.findStudent(1L)).thenReturn(new Student(1L, "Паша", 17));
        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Паша"));
    }

    @Test
    void createStudent() throws Exception {
        when(studentService.addStudent(new Student(null, "Маша", 17))).thenReturn(new Student(1L, "Маша", 17));
        mockMvc.perform(post("/student").content("{\"name\":\"Маша\",\"age\":17}").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Маша"));
    }

    @Test
    void putStudent() throws Exception {
        when(studentService.editStudent(new Student(1L, "Дима", 18))).thenReturn(new Student(1L, "Дима", 18));
        mockMvc.perform(put("/student").content("{\"id\":1,\"name\":\"Дима\",\"age\":18}").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(18));
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllStudent() throws Exception {
        when(studentService.getAllStudent()).thenReturn(List.of(new Student(1L, "Каша", 17)));
        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Каша"));
    }

    @Test
    void ageStudent() throws Exception {
        when(studentService.filterStudentsByAge(16)).thenReturn(List.of(new Student(1L, "Надя", 16)));
        mockMvc.perform(get("/student/filter?age=16"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(16));
    }

    @Test
    void findByAgeBetween() throws Exception {
        when(studentService.findByAgeBetween(16, 18)).thenReturn(List.of(new Student(1L, "Что", 17)));
        mockMvc.perform(get("/student/age-between?min=16&max=18"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(17));
    }
}