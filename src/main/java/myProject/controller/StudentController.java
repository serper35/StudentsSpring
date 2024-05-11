package myProject.controller;

import myProject.dto.StudentDto;
import myProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.get(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> add(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.add(studentDto));
    }

    @PutMapping
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.update(studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }
}
