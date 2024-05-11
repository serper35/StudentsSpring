package myProject.controller;

import myProject.dto.FacultyDto;
import myProject.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @PostMapping
    public ResponseEntity<FacultyDto> add(@RequestBody FacultyDto facultyDto) {
        return ResponseEntity.ok(facultyService.add(facultyDto));
    }

    @PutMapping
    public ResponseEntity<FacultyDto> update(@RequestBody FacultyDto facultyDto) {
        return ResponseEntity.ok(facultyService.update(facultyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
