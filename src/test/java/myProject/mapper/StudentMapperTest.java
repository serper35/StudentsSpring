package myProject.mapper;

import myProject.dto.StudentDto;
import myProject.entity.Faculty;
import myProject.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper = StudentMapper.INSTANCE;
    @Test
    void studentDtoToStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);
        studentDto.setAge(10);
        studentDto.setName("Vova");
        Faculty faculty = new Faculty(1L, "Math", 11);
        studentDto.setFaculty(faculty);

        Student student = studentMapper.studentDtoToStudent(studentDto);

        assertNotNull(student);
        assertEquals(1L, student.getId());
        assertEquals(10, student.getAge());
        assertEquals("Vova", student.getName());
        assertEquals(faculty, student.getFaculty());
    }

    @Test
    void studentToStudentDto() {
        Student student = new Student();
        student.setId(1L);
        student.setAge(10);
        student.setName("Vova");
        Faculty faculty = new Faculty(1L, "Math", 11);
        student.setFaculty(faculty);

        StudentDto studentDto = studentMapper.studentToStudentDto(student);

        assertNotNull(studentDto);
        assertEquals(1L, studentDto.getId());
        assertEquals(10, studentDto.getAge());
        assertEquals("Vova", studentDto.getName());
        assertEquals(faculty, studentDto.getFaculty());
    }

    @Test
    void studentsToDto() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        student.setAge(10);
        student.setName("Vova");
        Faculty faculty = new Faculty(1L, "Math", 11);
        student.setFaculty(faculty);
        students.add(student);

        List<StudentDto> studentDtoList = studentMapper.studentsToDto(students);

        assertNotNull(studentDtoList);
        assertEquals(1, studentDtoList.size());
        assertEquals(1L, studentDtoList.get(0).getId());
        assertEquals(10, studentDtoList.get(0).getAge());
        assertEquals("Vova", studentDtoList.get(0).getName());
        assertEquals(faculty, studentDtoList.get(0).getFaculty());
    }
}