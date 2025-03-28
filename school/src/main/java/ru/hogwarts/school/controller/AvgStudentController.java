package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.services.services.StudentService;

@RestController
public class AvgStudentController {

    StudentService studentService;

    public AvgStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/avg-student")
    public Double getAvgStudent() {
        return studentService.getAvgStudent();
    }
}
