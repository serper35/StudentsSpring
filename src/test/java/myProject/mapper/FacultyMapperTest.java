package myProject.mapper;

import myProject.dto.FacultyDto;
import myProject.entity.Faculty;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FacultyMapperTest {
    private FacultyMapper facultyMapper = FacultyMapper.INSTANCE;

    @Test
    void facultyToFacultyDto() {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Math");
        faculty.setNumberOfStudents(50);

        FacultyDto facultyDto = facultyMapper.facultyToFacultyDto(faculty);

        assertNotNull(facultyDto);
        assertEquals(1L, facultyDto.getId());
        assertEquals("Math", facultyDto.getName());
        assertEquals(50, facultyDto.getNumberOfStudents());
    }

    @Test
    void facultyDtoToFaculty() {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);
        facultyDto.setName("Math");
        facultyDto.setNumberOfStudents(50);

        Faculty faculty = facultyMapper.facultyDtoToFaculty(facultyDto);

        assertNotNull(faculty);
        assertEquals(1L, faculty.getId());
        assertEquals("Math", faculty.getName());
        assertEquals(50, faculty.getNumberOfStudents());
    }

    @Test
    void facultiesDtoToFaculties() {
        List<FacultyDto> facultyDtoList = new ArrayList<>();
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);
        facultyDto.setName("Math");
        facultyDto.setNumberOfStudents(50);
        facultyDtoList.add(facultyDto);

        List<Faculty> faculties = facultyMapper.facultiesDtoToFaculties(facultyDtoList);

        assertNotNull(faculties);
        assertEquals(1, faculties.size());
        assertEquals(1L, faculties.get(0).getId());
        assertEquals("Math", faculties.get(0).getName());
        assertEquals(50, faculties.get(0).getNumberOfStudents());
    }

    @Test
    void facultiesToFacultiesDto() {
        List<Faculty> faculties = new ArrayList<>();
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Math");
        faculty.setNumberOfStudents(50);
        faculties.add(faculty);

        List<FacultyDto> facultyDtoList = facultyMapper.facultiesToFacultiesDto(faculties);

        assertNotNull(facultyDtoList);
        assertEquals(1, facultyDtoList.size());
        assertEquals(1L, facultyDtoList.get(0).getId());
        assertEquals("Math", facultyDtoList.get(0).getName());
        assertEquals(50, facultyDtoList.get(0).getNumberOfStudents());
    }
}