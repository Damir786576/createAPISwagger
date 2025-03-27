package ru.hogwarts.school.services.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.services.impl.AllStudent;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class StudentService {

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;



    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent(long id) {
        studentRepository.deleteById(id);

    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> filterStudentsByAge(int age) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Long getAllStudentBySchool() {
        return studentRepository.getAllStudentBySchool();
    }

    public Double getAvgStudent() {
        return studentRepository.getAvgStudent();
    }

    public List<Student> getLastFiveStudent() {
        return studentRepository.getLastFiveStudent();
    }
}
