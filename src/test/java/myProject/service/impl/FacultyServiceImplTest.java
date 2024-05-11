package myProject.service.impl;

import myProject.dto.FacultyDto;
import myProject.entity.Faculty;
import myProject.mapper.FacultyMapper;
import myProject.repository.FacultyRepository;
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
class FacultyServiceImplTest {
    @InjectMocks
    private FacultyServiceImpl facultyService;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private FacultyMapper facultyMapper;

    @Test
    void addTestShouldWorkCorrectly() {
        String facultyName = "Math";
        int numberOfStudents = 50;

        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName(facultyName);
        facultyDto.setNumberOfStudents(numberOfStudents);

        Faculty faculty = new Faculty();
        faculty.setName(facultyName);
        faculty.setNumberOfStudents(numberOfStudents);

        FacultyDto expectedFacultyDto = new FacultyDto();
        expectedFacultyDto.setName(facultyName);
        expectedFacultyDto.setNumberOfStudents(numberOfStudents);
        expectedFacultyDto.setId(1L);

        when(facultyMapper.facultyDtoToFaculty(facultyDto)).thenReturn(faculty);
        when(facultyMapper.facultyToFacultyDto(faculty)).thenReturn(expectedFacultyDto);
        when(facultyRepository.save(faculty)).thenReturn(faculty);

        FacultyDto actual = facultyService.add(facultyDto);

        assertEquals(expectedFacultyDto, actual);
        verify(facultyMapper, times(1)).facultyDtoToFaculty(facultyDto);
        verify(facultyRepository, times(1)).save(faculty);
        verify(facultyMapper, times(1)).facultyToFacultyDto(faculty);
    }

    @Test
    void updateTestShouldWorkCorrectly() {
        long id = 1L;
        String newName = "New";
        int newNumberOfStudents = 500;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName("Old");
        faculty.setNumberOfStudents(300);

        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(id);
        facultyDto.setName("Old");
        facultyDto.setNumberOfStudents(newNumberOfStudents);

        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setId(id);
        updatedFaculty.setName(newName);
        updatedFaculty.setNumberOfStudents(newNumberOfStudents);

        FacultyDto updatedFacultyDto = new FacultyDto();
        updatedFacultyDto.setId(id);
        updatedFacultyDto.setName(newName);
        updatedFacultyDto.setNumberOfStudents(newNumberOfStudents);

        when(facultyMapper.facultyDtoToFaculty(facultyDto)).thenReturn(updatedFaculty);
        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(updatedFaculty)).thenReturn(updatedFaculty);
        when(facultyMapper.facultyToFacultyDto(updatedFaculty)).thenReturn(updatedFacultyDto);

        FacultyDto actual = facultyService.update(facultyDto);

        assertEquals(updatedFacultyDto, actual);
        assertNotEquals(facultyDto, actual);
        verify(facultyRepository, times(1)).findById(id);
        verify(facultyMapper, times(1)).facultyDtoToFaculty(facultyDto);
        verify(facultyRepository, times(1)).save(updatedFaculty);
        verify(facultyMapper, times(1)).facultyToFacultyDto(updatedFaculty);
    }

    @Test
    void updateShouldThrowNoSuchElementException() {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);

        when(facultyMapper.facultyDtoToFaculty(facultyDto)).thenReturn(faculty);
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> facultyService.update(facultyDto));
        verify(facultyMapper, times(1)).facultyDtoToFaculty(facultyDto);
        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, never()).save(any(Faculty.class));
        verify(facultyMapper, never()).facultyToFacultyDto(any(Faculty.class));
    }

    @Test
    void getTest() {
        long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        when(facultyMapper.facultyToFacultyDto(faculty)).thenReturn(facultyDto);

        FacultyDto actual = facultyService.get(id);

        assertEquals(facultyDto, actual);
        verify(facultyRepository, times(1)).findById(id);
        verify(facultyMapper, times(1)).facultyToFacultyDto(faculty);
    }

    @Test
    void getShouldThrowNoSuchElementException() {
        long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);

        when(facultyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> facultyService.get(id));
        verify(facultyMapper, never()).facultyToFacultyDto(any(Faculty.class));
    }

    @Test
    void deleteShouldThrowNoSuchElementException() {
        long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);

        when(facultyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,() ->facultyService.delete(id));
        verify(facultyRepository, never()).deleteById(id);
    }

    @Test
    void deleteTest() {
        long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        facultyService.delete(id);

        verify(facultyRepository, times(1)).findById(id);
        verify(facultyRepository, times(1)).deleteById(id);
    }

    @Test
    void getAll() {
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty());
        facultyList.add(new Faculty());

        List<FacultyDto> facultyDtoList = new ArrayList<>();
        facultyDtoList.add(new FacultyDto());
        facultyDtoList.add(new FacultyDto());

        when(facultyRepository.findAll()).thenReturn(facultyList);
        when(facultyMapper.facultiesToFacultiesDto(facultyList)).thenReturn(facultyDtoList);

        List<FacultyDto> actual = facultyService.getAll();

        assertEquals(facultyDtoList, actual);
        verify(facultyRepository, times(1)).findAll();
        verify(facultyMapper, times(1)).facultiesToFacultiesDto(facultyList);
    }
}