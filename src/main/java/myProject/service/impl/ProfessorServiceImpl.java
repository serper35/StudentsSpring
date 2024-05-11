package myProject.service.impl;

import jakarta.transaction.Transactional;
import myProject.dto.ProfessorDto;
import myProject.entity.Professor;
import myProject.mapper.ProfessorMapper;
import myProject.repository.ProfessorRepository;
import myProject.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private ProfessorRepository professorRepository;
    private ProfessorMapper professorMapper;
    private String notFound = "Professor not found";

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    @Transactional
    public ProfessorDto add(ProfessorDto professorDto) {
        Professor professor = professorMapper.professorDtoToProfessor(professorDto);

        Professor savedProfessor = professorRepository.save(professor);

        return professorMapper.professorToProfessorDto(savedProfessor);
    }

    @Override
    @Transactional
    public ProfessorDto update(ProfessorDto professorDto) {
        Professor professor = professorMapper.professorDtoToProfessor(professorDto);
        professorRepository.findById(professor.getId()).orElseThrow(() -> new NoSuchElementException(notFound));
        Professor savedProfessor = professorRepository.save(professor);

        return professorMapper.professorToProfessorDto(savedProfessor);
    }

    @Override
    @Transactional
    public ProfessorDto get(long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new NoSuchElementException(notFound));

        return professorMapper.professorToProfessorDto(professor);
    }

    @Override
    @Transactional
    public void delete(long id) {
        professorRepository.findById(id).orElseThrow(() -> new NoSuchElementException(notFound));

        professorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ProfessorDto> getAll() {
        List<Professor> professors = professorRepository.findAll();

        return professorMapper.professorsDto(professors);
    }
}
