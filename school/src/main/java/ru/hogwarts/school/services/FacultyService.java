package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;

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
}
