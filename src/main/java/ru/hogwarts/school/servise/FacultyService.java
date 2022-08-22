package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private Long lastId = 0L;

    public Faculty addFaculty(Faculty faculty) {     //метод добавления факультета               C
        faculty.setId(++lastId);                     //ид.номера для факультетов
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id){             // По ид.номеру можем найти факультет       R
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {     // Редактируем факультет                    U
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return  null;
    }

    public Faculty deleteFaculty(Long id){           // Удаляем факультет.                       D
        return  faculties.remove(id);
    }

    public Collection<Faculty> findColorFaculty(String color){
        return    faculties.values().stream().filter(faculty -> faculty.getColor().equals(color)).collect(Collectors.toList());

    }

}
