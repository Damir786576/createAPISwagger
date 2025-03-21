package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.services.services.StudentService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestStudent {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void add() {
        Student kasha = new Student(null, "Каша", 21);
        Student savedKasha = new Student(3L, "Каша", 21);
        when(studentRepository.save(kasha)).thenReturn(savedKasha);
        assertEquals(3L, studentService.addStudent(kasha).getId());
        verify(studentRepository).save(kasha);
    }

    @Test
    public void remove() {
        studentService.removeStudent(1L);
        verify(studentRepository).deleteById(1L);
    }

    @Test
    public void find() {
        Student misha = new Student(1L, "Миша", 19);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(misha));

        assertEquals("Миша", studentService.findStudent(1L).getName());
        verify(studentRepository).findById(1L);
    }

    @Test
    public void findAll() {
        Student misha = new Student(1L, "Миша", 19);
        Student pasha = new Student(2L, "Паша", 20);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(misha, pasha));
        assertEquals(2, studentService.getAllStudent().size());
        verify(studentRepository).findAll();
    }

    @Test
    public void edit() {
        Student updated = new Student(1L, "Паша", 21);
        when(studentRepository.save(updated)).thenReturn(updated);
        assertEquals(updated, studentService.editStudent(updated));
        verify(studentRepository).save(updated);
    }

    @Test
    public void filterByAge() {
        Student misha = new Student(1L, "Миша", 19);
        Student pasha = new Student(2L, "Паша", 20);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(misha, pasha));
        assertEquals(1, studentService.filterStudentsByAge(20).size());
        verify(studentRepository).findAll();
    }
}