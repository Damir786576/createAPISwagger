package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;


    public Student addStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student removeStudent(long id) {
        return students.remove(id);

    }

    public Collection<Student> getAllStudent() {
        return students.values();
    }

    public List<Student> filterStudentsByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
