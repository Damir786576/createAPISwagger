package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.services.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    @Autowired
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> putFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.editFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllStudent() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Faculty>> filterFacultiesByColor(@RequestParam String color) {
        List<Faculty> filteredFaculties = facultyService.filterColourbyStudent(color);
        return ResponseEntity.ok(filteredFaculties);
    }
}


