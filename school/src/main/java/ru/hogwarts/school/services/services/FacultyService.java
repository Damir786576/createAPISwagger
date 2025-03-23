package ru.hogwarts.school.services.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private final FacultyRepository facultyRepository;

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();

    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public List<Faculty> filterColourbyStudent(String colour) {
        return facultyRepository.findAll().stream()
                .filter(faculty -> colour.equalsIgnoreCase(faculty.getColor()))
                .collect(Collectors.toList());
    }

    public List<Faculty> findByNameOrColourIgnoreCase(String name, String colour) {
        return facultyRepository.findByNameOrColourIgnoreCase(name, colour);
    }
}
