package ru.hogwarts.school.services.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Добавляет факультет");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Ищет факультет по уникальному номеру");
        return facultyRepository.findById(id).orElse(null);

    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Исправляет информацию про факультет");
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        logger.info("Удаляет факультет по уникальному номеру");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {
        logger.info("Получает список всех факультетов");
        return facultyRepository.findAll();
    }

    public List<Faculty> filterColourbyStudent(String colour) {
        logger.info("Список студентов в определенном факультете");
        return facultyRepository.findAll().stream()
                .filter(faculty -> colour.equalsIgnoreCase(faculty.getColour()))
                .collect(Collectors.toList());
    }

    public List<Faculty> findByNameOrColourIgnoreCase(String name, String colour) {
        logger.info("Ищет факультет по имени или цвету");
        return facultyRepository.findByNameOrColourIgnoreCase(name, colour);
    }
}
