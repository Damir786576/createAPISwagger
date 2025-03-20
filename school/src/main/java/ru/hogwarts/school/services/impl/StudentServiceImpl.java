package ru.hogwarts.school.services.impl;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentServiceImpl {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent(Student student);
    Student removeStudent(long id);
    Collection<Student> getAllStudent();
    List<Student> filterStudentsByAge(int age);
}
