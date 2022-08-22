package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>(); // журнал студентов
    private Long lastId = 0L;                         // идентификационный номер студента

    public Student addStudent(Student student) {     //метод добавления студента в журнал                              C
        student.setId(++lastId);                     //присвоение поступившему студенту ид.номера
        students.put(lastId, student);               //зачисление студента, постановка на довольствие.
        return student;                              //студент, будь готов к учебе и хоз работам.
    }

    public Student findStudent(Long id) {             // По ид.номеру можем найти студента                              R
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {//Если найден студент по ключу, то редактируем студенту чего нибудь)))                            U
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long id) {           // Отчисляем студента без объявления причин, удаляем из журнала.  D
        return students.remove(id);
    }



    public Collection<Student> findAge(int age) {
        return students.values().stream().filter(student -> student.getAge() == age).collect(Collectors.toList());
    }

    public int getSize() {
        return students.size();
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }
}


