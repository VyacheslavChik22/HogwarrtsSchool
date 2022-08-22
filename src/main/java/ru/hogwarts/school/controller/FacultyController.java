package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    //Заинжектим FacultyService для дальнейшего использования
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")                                        //http:/localhost:8080/faculty/1...  выводим факультет
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {                                    //Если факультет не найден:
            return   ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping                                               //http:/localhost:8080/faculty
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {

        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if(foundFaculty == null){                                          // Если факультет не найден:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public Faculty removeFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);

    }

    @GetMapping
    public Faculty findColorFaculty(@RequestParam(required = false) String color){
        return (Faculty) facultyService.findColorFaculty(color);
    }
}

