package myProject.controller;

import myProject.dto.ProfessorDto;
import myProject.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> getAll() {
        return ResponseEntity.ok(professorService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> add(@RequestBody ProfessorDto professorDto) {
        return ResponseEntity.ok(professorService.add(professorDto));
    }

    @PutMapping
    public ResponseEntity<ProfessorDto> udpate(@RequestBody ProfessorDto professorDto) {
        return ResponseEntity.ok(professorService.update(professorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
