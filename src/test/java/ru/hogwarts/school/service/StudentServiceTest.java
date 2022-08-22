package ru.hogwarts.school.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentServiceTest {

    ru.hogwarts.school.service.StudentService studentService;

    private static final Student STUDENT = new Student(1L, "Garry", 12);
    private static final Student STUDENT2 = new Student(2L, "Gretta", 15);
    private static final Student STUDENT3 = new Student(1L, "Garry", 15);

    @BeforeEach
    void setUpp() {
        studentService = new ru.hogwarts.school.service.StudentService();
    }

    @Test
    void shouldAddStudent() {
        Student result = studentService.addStudent(STUDENT);
        assertThat(result).isEqualTo(STUDENT);
        Assertions.assertThat(studentService.getSize()).isEqualTo(1);
    }

    @Test
    void shouldFindStudent() {
        studentService.addStudent(STUDENT);
        Assertions.assertThat(studentService.findStudent(1L)).isEqualTo(STUDENT);
        studentService.addStudent(STUDENT2);
        Assertions.assertThat(studentService.findStudent(2L)).isEqualTo(STUDENT2);
    }

    @Test
    void editStudent() {
        studentService.addStudent(STUDENT);
        Student result = studentService.editStudent(new Student(1L, "Garry", 15));
        assertThat(result).isEqualTo(STUDENT3);
    }

    @Test
    void shouldDeleteStudent() {

        studentService.addStudent(STUDENT);
        studentService.addStudent(STUDENT2);
        studentService.deleteStudent(1L);
        Assertions.assertThat(studentService.getSize()).isEqualTo(1);

    }

    @Test
    void findAge() {
        studentService.addStudent(STUDENT);
        studentService.addStudent(STUDENT2);
        Assertions.assertThat(studentService.findAge(12)).containsExactlyInAnyOrder(STUDENT);
    }


    @Test
    void getAllStudents(){
        studentService.addStudent(STUDENT);
        studentService.addStudent(STUDENT2);
        Assertions.assertThat(studentService.getSize()).isEqualTo(2);

    }

}

