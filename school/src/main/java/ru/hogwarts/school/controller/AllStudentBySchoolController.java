package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.services.impl.AllStudent;
import ru.hogwarts.school.services.services.StudentService;

import java.util.List;

@RestController
public class AllStudentBySchoolController {

    private final StudentService studentService;

    public AllStudentBySchoolController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student-by-school")
    public Long getAllStudentBySchool() {
        return studentService.getAllStudentBySchool();
    }

}
