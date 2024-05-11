package myProject.service;

import myProject.dto.StudentDto;
import myProject.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDto add(StudentDto studentDto);

    StudentDto get(long id);

    StudentDto update(StudentDto studentDto);

    void delete(long id);

    List<StudentDto> getAll();


}
