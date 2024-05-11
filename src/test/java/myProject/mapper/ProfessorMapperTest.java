package myProject.mapper;

import myProject.dto.ProfessorDto;
import myProject.entity.Professor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorMapperTest {
    private ProfessorMapper professorMapper = ProfessorMapper.INSTANCE;

    @Test
    void professorDtoToProfessor() {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(1L);
        professorDto.setName("Voba");

        Professor professor = professorMapper.professorDtoToProfessor(professorDto);

        assertNotNull(professor);
        assertEquals(1L, professor.getId());
        assertEquals("Voba", professor.getName());
    }

    @Test
    void professorToProfessorDto() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Voba");

        ProfessorDto professorDto = professorMapper.professorToProfessorDto(professor);

        assertNotNull(professorDto);
        assertEquals(1L, professorDto.getId());
        assertEquals("Voba", professorDto.getName());
    }

    @Test
    void professorsDto() {
        List<Professor> professors = new ArrayList<>();
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Voba");
        professors.add(professor);

        List<ProfessorDto> professorDtoList = professorMapper.professorsDto(professors);

        assertNotNull(professorDtoList);
        assertEquals(1, professorDtoList.size());
        assertEquals(1L, professorDtoList.get(0).getId());
        assertEquals("Voba", professorDtoList.get(0).getName());
    }
}