package ru.hogwarts.school.services.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Добавляет студентов");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Ищет студента по уникальному номеру");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Исправляет информацию про студента");
        return studentRepository.save(student);
    }

    public void removeStudent(long id) {
        logger.info("Удаляет студента по уникальному номеру");
        studentRepository.deleteById(id);

    }

    public Collection<Student> getAllStudent() {
        logger.info("Получает список всех студентов");
        return studentRepository.findAll();
    }

    public List<Student> filterStudentsByAge(int age) {
        logger.info("Показывает список студентов, которым N лет");
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Student> findByAgeBetween(Integer min, Integer max) {
        logger.info("Показывает список студентов, по указонному возрасту");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Long getAllStudentBySchool() {
        logger.info("Количество всех студентов");
        return studentRepository.getAllStudentBySchool();
    }

    public Double getAvgStudent() {
        logger.info("Средний возраст студентов");
        return studentRepository.getAvgStudent();
    }

    public List<Student> getLastFiveStudent() {
        logger.info("5 последних студентов");
        return studentRepository.getLastFiveStudent();
    }
}
