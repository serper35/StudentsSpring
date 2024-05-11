package myProject.service;

import myProject.dto.ProfessorDto;

import java.util.List;

public interface ProfessorService {
    ProfessorDto add(ProfessorDto professorDto);

    ProfessorDto update(ProfessorDto professorDto);

    ProfessorDto get(long id);

    void delete(long id);

    List<ProfessorDto> getAll();
}
