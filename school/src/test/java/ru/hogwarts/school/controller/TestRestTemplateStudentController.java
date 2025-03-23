package ru.hogwarts.school.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateStudentController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    void getStudent() {
        Student student = studentRepository.save(new Student(null, "Паша", 17));
        assertEquals("Паша", restTemplate.getForObject(getUrl() + "/" + student.getId(), Student.class).getName());
    }

    @Test
    void createStudent() {
        Student student = new Student(null, "Миша", 17);
        assertEquals("Миша", restTemplate.postForObject(getUrl(), student, Student.class).getName());
    }

    @Test
    void putStudent() {
        Student student = studentRepository.save(new Student(null, "Маша", 17));
        student.setAge(18);
        assertEquals(18, restTemplate.exchange(getUrl(), HttpMethod.PUT, new HttpEntity<>(student), Student.class).getBody().getAge());
    }

    @Test
    void deleteStudent() {
        Student student = studentRepository.save(new Student(null, "Даша", 17));
        restTemplate.delete(getUrl() + "/" + student.getId());
        assertEquals(0, studentRepository.count());
    }

    @Test
    void getAllStudent() {
        studentRepository.save(new Student(null, "Каша", 17));
        assertEquals(1, restTemplate.getForObject(getUrl(), Student[].class).length);
    }

    @Test
    void ageStudent() {
        studentRepository.save(new Student(null, "Ваша", 16));
        assertEquals(16, restTemplate.getForObject(getUrl() + "/filter?age=16", Student[].class)[0].getAge());
    }

    @Test
    void findByAgeBetween() {
        studentRepository.save(new Student(null, "Наша", 17));
        assertEquals(17, restTemplate.getForObject(getUrl() + "/age-between?min=16&max=18", Student[].class)[0].getAge());
    }

    private String getUrl() {
        return "http://localhost:" + port + "/student";
    }
}