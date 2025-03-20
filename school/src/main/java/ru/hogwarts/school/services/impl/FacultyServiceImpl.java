package ru.hogwarts.school.services.impl;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyServiceImpl {
    Faculty addFaculty(Faculty name);
    Faculty findFaculty(long id);
    Faculty editFaculty(Faculty name);
    Faculty removeFaculty(long id);
    Collection<Faculty> getAllFaculty();
    List<Faculty> filterColourbyStudent(String colour);
}
