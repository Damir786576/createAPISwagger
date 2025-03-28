package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.services.StudentService;

import java.util.List;

@RestController
public class LastFiveStudentController {
    private StudentService studentService;

    public LastFiveStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/last-five-student")
    public List<Student> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }


}
