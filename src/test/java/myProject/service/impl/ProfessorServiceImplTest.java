package myProject.service.impl;

import myProject.dto.ProfessorDto;
import myProject.entity.Professor;
import myProject.mapper.ProfessorMapper;
import myProject.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceImplTest {
    @InjectMocks
    private ProfessorServiceImpl professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private ProfessorMapper professorMapper;

    @Test
    void addTestShouldWorkCorrectly() {
        String name = "Vova";
        Professor professor = new Professor();
        professor.setName(name);

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName(name);

        ProfessorDto excpectedProfessorDto = new ProfessorDto();
        professorDto.setName(name);
        professorDto.setId(1L);

        when(professorMapper.professorDtoToProfessor(professorDto)).thenReturn(professor);
        when(professorRepository.save(professor)).thenReturn(professor);
        when(professorMapper.professorToProfessorDto(professor)).thenReturn(excpectedProfessorDto);

        ProfessorDto actual = professorService.add(professorDto);

        assertEquals(excpectedProfessorDto, actual);
        verify(professorMapper, times(1)).professorToProfessorDto(professor);
        verify(professorMapper, times(1)).professorDtoToProfessor(professorDto);
        verify(professorRepository, times(1)).save(professor);
    }

    @Test
    void updateShouldWorkCorrectly() {
        String name = "Vova";
        Long id = 1L;
        Professor professor = new Professor();
        professor.setName(name);
        professor.setId(id);

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName(name);
        professorDto.setId(id);

        ProfessorDto excpectedProfessorDto = new ProfessorDto();
        professorDto.setName("Vovan");
        professorDto.setId(id);

        when(professorMapper.professorDtoToProfessor(professorDto)).thenReturn(professor);
        when(professorRepository.save(professor)).thenReturn(professor);
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(professorMapper.professorToProfessorDto(professor)).thenReturn(excpectedProfessorDto);

        ProfessorDto actual = professorService.update(professorDto);

        assertEquals(excpectedProfessorDto, actual);
        assertNotEquals(professorDto, actual);
        verify(professorMapper, times(1)).professorToProfessorDto(professor);
        verify(professorMapper, times(1)).professorDtoToProfessor(professorDto);
        verify(professorRepository, times(1)).save(professor);
        verify(professorRepository, times(1)).findById(1L);
    }

    @Test
    void updateShouldThrowNoSuchElementException() {
        String name = "Vova";
        Long id = 1L;
        Professor professor = new Professor();
        professor.setName(name);
        professor.setId(id);

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName(name);
        professorDto.setId(id);

        when(professorMapper.professorDtoToProfessor(professorDto)).thenReturn(professor);
        when(professorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> professorService.update(professorDto));
        verify(professorMapper, times(1)).professorDtoToProfessor(professorDto);
        verify(professorRepository, times(1)).findById(1L);
        verify(professorRepository, never()).save(any(Professor.class));
        verify(professorMapper, never()).professorToProfessorDto(any(Professor.class));
    }

    @Test
    void getShouldWorkCorrectly() {
        String name = "Vova";
        Long id = 1L;
        Professor professor = new Professor();
        professor.setName(name);
        professor.setId(id);

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName(name);
        professorDto.setId(id);


        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(professorMapper.professorToProfessorDto(professor)).thenReturn(professorDto);

        ProfessorDto actual = professorService.get(id);

        assertEquals(professorDto, actual);
        verify(professorMapper, times(1)).professorToProfessorDto(professor);
        verify(professorRepository, times(1)).findById(id);
    }

    @Test
    void getShouldThrowNoSuchElementException() {
        String name = "Vova";
        Long id = 1L;
        Professor professor = new Professor();
        professor.setName(name);
        professor.setId(id);

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName(name);
        professorDto.setId(id);
        ;

        when(professorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> professorService.get(id));
        verify(professorMapper, never()).professorToProfessorDto(any(Professor.class));
    }

    @Test
    void deleteTest() {
        String name = "Vova";
        Long id = 1L;
        Professor professor = new Professor();
        professor.setName(name);
        professor.setId(id);

        when(professorRepository.findById(id)).thenReturn(Optional.of(professor));

        professorService.delete(id);

        verify(professorRepository, times(1)).findById(id);
        verify(professorRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteShouldThrowNoSuchElementException() {
        String name = "Vova";
        Long id = 1L;
        Professor professor = new Professor();
        professor.setName(name);
        professor.setId(id);

        when(professorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,() ->professorService.delete(id));
        verify(professorRepository, never()).deleteById(id);
    }

    @Test
    void getAll() {
        List<Professor> professorList = new ArrayList<>();
        professorList.add(new Professor());
        professorList.add(new Professor());

        List<ProfessorDto> professorDtoList = new ArrayList<>();
        professorDtoList.add(new ProfessorDto());
        professorDtoList.add(new ProfessorDto());

        when(professorRepository.findAll()).thenReturn(professorList);
        when(professorMapper.professorsDto(professorList)).thenReturn(professorDtoList);

        List<ProfessorDto> actual = professorService.getAll();

        assertEquals(professorDtoList, actual);
        verify(professorRepository, times(1)).findAll();
        verify(professorMapper, times(1)).professorsDto(professorList);
    }
}