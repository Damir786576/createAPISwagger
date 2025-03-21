package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.services.services.FacultyService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestFaculty {

    @InjectMocks
    private FacultyService facultyService;

    @Mock
    private FacultyRepository facultyRepository;

    @Test
    public void add() {
        Faculty hufflepuff = new Faculty(null, "Пуффендуй", "Желтый");
        Faculty savedHufflepuff = new Faculty(3L, "Пуффендуй", "Желтый");
        when(facultyRepository.save(hufflepuff)).thenReturn(savedHufflepuff);
        assertEquals(3L, facultyService.addFaculty(hufflepuff).getId());
        verify(facultyRepository).save(hufflepuff);
    }

    @Test
    public void remove() {
        facultyService.removeFaculty(1L);
        verify(facultyRepository).deleteById(1L);
    }

    @Test
    public void find() {
        Faculty gryffindor = new Faculty(1L, "Гриффиндор", "Красный");
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(gryffindor));
        assertEquals("Гриффиндор", facultyService.findFaculty(1L).getName());
        verify(facultyRepository).findById(1L);
    }

    @Test
    public void findAll() {
        Faculty gryffindor = new Faculty(1L, "Гриффиндор", "Красный");
        Faculty slytherin = new Faculty(2L, "Слизерин", "Зеленый");
        when(facultyRepository.findAll()).thenReturn(Arrays.asList(gryffindor, slytherin));

        assertEquals(2, facultyService.getAllFaculty().size());
        verify(facultyRepository).findAll();
    }

    @Test
    public void edit() {
        Faculty updatedFaculty = new Faculty(1L, "Гриффиндор", "Алый");
        when(facultyRepository.save(updatedFaculty)).thenReturn(updatedFaculty);

        assertEquals(updatedFaculty, facultyService.editFaculty(updatedFaculty));
        verify(facultyRepository).save(updatedFaculty);
    }

    @Test
    public void filterByColour() {
        Faculty gryffindor = new Faculty(1L, "Гриффиндор", "Красный");
        Faculty slytherin = new Faculty(2L, "Слизерин", "Зеленый");
        when(facultyRepository.findAll()).thenReturn(Arrays.asList(gryffindor, slytherin));

        assertEquals(1, facultyService.filterColourbyStudent("Зеленый").size());
        verify(facultyRepository).findAll();
    }
}