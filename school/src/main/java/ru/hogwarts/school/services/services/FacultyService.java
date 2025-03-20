package ru.hogwarts.school.services.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> facultys = new HashMap<>();
    private long lastId = 0;

    public Faculty addFaculty(Faculty name) {
        name.setId(++lastId);
        facultys.put(lastId, name);
        return name;
    }

    public Faculty findFaculty(long id) {
        return facultys.get(id);

    }

    public Faculty editFaculty(Faculty name) {
        facultys.put(name.getId(), name);
        return name;
    }

    public Faculty removeFaculty(long id) {
        return facultys.remove(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultys.values();
    }

    public List<Faculty> filterColourbyStudent(String colour) {
        return facultys.values().stream()
                .filter(faculty -> colour.equalsIgnoreCase(faculty.getColor()))
                .collect(Collectors.toList());

    }
}
