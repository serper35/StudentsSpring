package myProject.service.impl;

import myProject.dto.StudentDto;
import myProject.entity.Student;
import myProject.mapper.StudentMapper;
import myProject.repository.StudentRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void addTestShouldWorkCorrectly() {
        String name = "Vova";
        int age = 10;
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        studentDto.setAge(age);

        StudentDto excpectedStudentDto = new StudentDto();
        excpectedStudentDto.setName(name);
        excpectedStudentDto.setAge(age);
        excpectedStudentDto.setId(1L);

        when(studentMapper.studentDtoToStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.studentToStudentDto(student)).thenReturn(excpectedStudentDto);

        StudentDto actual = studentService.add(studentDto);

        assertEquals(excpectedStudentDto, actual);
        verify(studentMapper, times(1)).studentToStudentDto(student);
        verify(studentMapper, times(1)).studentDtoToStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void updateShouldWorkCorrectly() {
        String name = "Vova";
        int age = 10;
        Long id = 1L;

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        studentDto.setAge(age);
        studentDto.setId(id);

        StudentDto excpectedStudentDto = new StudentDto();
        excpectedStudentDto.setName("Vovan");
        excpectedStudentDto.setAge(age);
        excpectedStudentDto.setId(1L);

        when(studentMapper.studentDtoToStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.studentToStudentDto(student)).thenReturn(excpectedStudentDto);

        StudentDto actual = studentService.update(studentDto);

        assertEquals(excpectedStudentDto, actual);
        assertNotEquals(studentDto, actual);
        verify(studentMapper, times(1)).studentToStudentDto(student);
        verify(studentMapper, times(1)).studentDtoToStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void updateShouldThrowNoSuchElementException() {
        String name = "Vova";
        int age = 10;
        Long id = 1L;

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        studentDto.setAge(age);
        studentDto.setId(id);

        when(studentMapper.studentDtoToStudent(studentDto)).thenReturn(student);
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.update(studentDto));
        verify(studentMapper, times(1)).studentDtoToStudent(studentDto);
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, never()).save(any(Student.class));
        verify(studentMapper, never()).studentToStudentDto(any(Student.class));
    }

    @Test
    void getShouldWorkCorrectly() {
        String name = "Vova";
        int age = 10;
        Long id = 1L;

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        studentDto.setAge(age);
        studentDto.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.studentToStudentDto(student)).thenReturn(studentDto);

        StudentDto actual = studentService.get(id);

        assertEquals(studentDto, actual);
        verify(studentMapper, times(1)).studentToStudentDto(student);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void getShouldThrowNoSuchElementException() {
        String name = "Vova";
        int age = 10;
        Long id = 1L;

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        studentDto.setAge(age);
        studentDto.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.get(id));
        verify(studentMapper, never()).studentToStudentDto(any(Student.class));
    }

    @Test
    void deleteTest() {
        String name = "Vova";
        int age = 10;
        Long id = 1L;

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        studentService.delete(id);

        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteShouldThrowNoSuchElementException() {
        String name = "Vova";
        int age = 10;
        Long id = 1L;

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,() ->studentService.delete(id));
        verify(studentRepository, never()).deleteById(id);
    }

    @Test
    void getAll() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student());
        studentList.add(new Student());

        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList.add(new StudentDto());
        studentDtoList.add(new StudentDto());

        when(studentRepository.findAll()).thenReturn(studentList);
        when(studentMapper.studentsToDto(studentList)).thenReturn(studentDtoList);

        List<StudentDto> actual = studentService.getAll();

        assertEquals(studentDtoList, actual);
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, times(1)).studentsToDto(studentList);
    }
}