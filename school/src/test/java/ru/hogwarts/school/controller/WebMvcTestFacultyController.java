package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.services.FacultyService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class WebMvcTestFacultyController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void testGetFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red");
        when(facultyService.findFaculty(1L)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty(null, "Hufflepuff", "Yellow");
        when(facultyService.addFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hufflepuff\",\"color\":\"Yellow\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hufflepuff"));
    }

    @Test
    void testPutFaculty() throws Exception {
        Faculty updatedFaculty = new Faculty(1L, "Slytherin Updated", "Green");
        when(facultyService.editFaculty(any(Faculty.class))).thenReturn(updatedFaculty);

        mockMvc.perform(put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Slytherin Updated\",\"color\":\"Green\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Slytherin Updated"));
    }

    @Test
    void testDeleteFaculty() throws Exception {
        doNothing().when(facultyService).removeFaculty(1L);

        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllFaculties() throws Exception {
        List<Faculty> faculties = List.of(new Faculty(1L, "Gryffindor", "Red"));
        when(facultyService.getAllFaculty()).thenReturn(faculties);

        mockMvc.perform(get("/faculty/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gryffindor"));
    }

    @Test
    void testFilterFacultiesByColor() throws Exception {
        List<Faculty> faculties = List.of(new Faculty(1L, "Hufflepuff", "Yellow"));
        when(facultyService.filterColourbyStudent("Yellow")).thenReturn(faculties);

        mockMvc.perform(get("/faculty/filter?color=Yellow"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hufflepuff"));
    }

    @Test
    void testFindByNameOrColourIgnoreCase() throws Exception {
        List<Faculty> faculties = List.of(new Faculty(1L, "Ravenclaw", "Blue"));
        when(facultyService.findByNameOrColourIgnoreCase("Ravenclaw", "Blue")).thenReturn(faculties);

        mockMvc.perform(get("/faculty/get?name=Ravenclaw&colour=Blue"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ravenclaw"));
    }
}