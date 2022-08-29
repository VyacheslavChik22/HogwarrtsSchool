package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.servise.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    //заинжектим StudentService для использования его методов

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping                                               //http:/localhost:8080/student
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }


    @GetMapping("{id}")                                        //http:/localhost:8080/student/1...  выводим студента
    public ResponseEntity<Student> getStudentId(@PathVariable Long id) {

        Student student = studentService.getStudentId(id);
        if (student == null) {                                     // Если студент не найден:
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }


    @GetMapping                       // фильтр студентов
    public ResponseEntity findStudents(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) Long id,
                                       @RequestParam(required = false) int age,int age2,
                                       @RequestParam(required = false) String namePart
                                      )
    {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }

        if (namePart != null && !namePart.isBlank()) {
            return ResponseEntity.ok(studentService.findAllByNamePart(namePart));
        }

        if (age >= 10 && age <= 20 ) {  //Студенты зачисляются с 11 лет, учатся 7 лет и не могут быть старше 20ти лет.
            return ResponseEntity.ok(studentService.findStudentsByAge(age, age2));
        }
        if (id > 0 ) {
            return ResponseEntity.ok(studentService.getStudentId(id));
        }

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {                                             // Если студент не найден:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

