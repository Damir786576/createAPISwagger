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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateFacultyController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void testGetFaculty() {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        ResponseEntity<Faculty> response = restTemplate.getForEntity(baseUrl() + "/1", Faculty.class);
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
        Faculty faculty = new Faculty(1L, "Slytherin", "Green");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        Faculty updatedFaculty = new Faculty(1L, "Slytherin Updated", "Green");
        HttpEntity<Faculty> request = new HttpEntity<>(updatedFaculty);
        ResponseEntity<Faculty> response = restTemplate.exchange(baseUrl(), HttpMethod.PUT, request, Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Slytherin Updated", response.getBody().getName());
    }

    @Test
    void testDeleteFaculty() {
        Faculty faculty = new Faculty(1L, "Ravenclaw", "Blue");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl() + "/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllFaculties() {
        Faculty faculty1 = new Faculty(null, "Gryffindor", "Red");
        Faculty faculty2 = new Faculty(null, "Slytherin", "Green");
        restTemplate.postForEntity(baseUrl(), faculty1, Faculty.class);
        restTemplate.postForEntity(baseUrl(), faculty2, Faculty.class);

        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl(), List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() >= 2);
    }

    @Test
    void testFilterFacultiesByColor() {
        Faculty faculty = new Faculty(null, "Hufflepuff", "Yellow");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl() + "/filter?color=Yellow", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testFindByNameOrColourIgnoreCase() {
        Faculty faculty = new Faculty(null, "Ravenclaw", "Blue");
        restTemplate.postForEntity(baseUrl(), faculty, Faculty.class);

        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl() + "/get?name=Ravenclaw&colour=Blue", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }
    private String baseUrl() {
        return "http://localhost:" + port + "/student";
    }
}