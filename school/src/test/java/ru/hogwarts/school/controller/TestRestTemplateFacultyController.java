package ru.hogwarts.school.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateFacultyController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {
        facultyRepository.deleteAll();
    }

    @Test
    void testGetFaculty() {
        Faculty faculty = new Faculty(null, "Gryffindor", "Red");
        Faculty savedFaculty = restTemplate.postForEntity(baseUrl(), faculty, Faculty.class).getBody();

        ResponseEntity<Faculty> response = restTemplate.getForEntity(baseUrl() + "/" + savedFaculty.getId(), Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Gryffindor", response.getBody().getName());
    }

    @Test
    void testCreateFaculty() {
        Faculty faculty = new Faculty(null, "Hufflepuff", "Yellow");
        ResponseEntity<Faculty> response = restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hufflepuff", response.getBody().getName());
    }

    @Test
    void testPutFaculty() {
        Faculty faculty = new Faculty(null, "Slytherin", "Green");
        Faculty savedFaculty = restTemplate.postForEntity(baseUrl(), faculty, Faculty.class).getBody();

        Faculty updatedFaculty = new Faculty(savedFaculty.getId(), "Slytherin Updated", "Green");
        HttpEntity<Faculty> request = new HttpEntity<>(updatedFaculty);
        ResponseEntity<Faculty> response = restTemplate.exchange(baseUrl(), HttpMethod.PUT, request, Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Slytherin Updated", response.getBody().getName());
    }

    @Test
    void testDeleteFaculty() {
        Faculty faculty = new Faculty(null, "Ravenclaw", "Blue");
        Faculty savedFaculty = restTemplate.postForEntity(baseUrl(), faculty, Faculty.class).getBody();

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl() + "/" + savedFaculty.getId(), HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllFaculties() {
        Faculty faculty1 = new Faculty(null, "Gryffindor", "Red");
        Faculty faculty2 = new Faculty(null, "Slytherin", "Green");
        restTemplate.postForEntity(baseUrl(), faculty1, Faculty.class);
        restTemplate.postForEntity(baseUrl(), faculty2, Faculty.class);

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(baseUrl() + "/all", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().length);
    }

    @Test
    void testFilterFacultiesByColor() {
        Faculty faculty = new Faculty(null, "Hufflepuff", "Yellow");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(baseUrl() + "/filter?color=Yellow", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().length == 0);
    }

    @Test
    void testFindByNameOrColourIgnoreCase() {
        Faculty faculty = new Faculty(null, "Ravenclaw", "Blue");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(baseUrl() + "/get?name=Ravenclaw&colour=Blue", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().length == 0);
    }

    private String baseUrl() {
        return "http://localhost:" + port + "/faculty";
    }
}