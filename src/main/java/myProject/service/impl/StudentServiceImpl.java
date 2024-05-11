package myProject.service.impl;

import jakarta.transaction.Transactional;
import myProject.dto.StudentDto;
import myProject.entity.Student;
import myProject.mapper.StudentMapper;
import myProject.repository.StudentRepository;
import myProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private String notFound = "Student not found";

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public StudentDto add(StudentDto studentDto) {
        Student student = studentMapper.studentDtoToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.studentToStudentDto(savedStudent);
    }

    @Override
    @Transactional
    public StudentDto get(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(notFound));
        return studentMapper.studentToStudentDto(student);
    }

    @Override
    @Transactional
    public StudentDto update(StudentDto studentDto) {
        Student student = studentMapper.studentDtoToStudent(studentDto);
        studentRepository.findById(student.getId()).orElseThrow(() -> new  NoSuchElementException(notFound));
        Student savedStudent = studentRepository.save(student);

        return studentMapper.studentToStudentDto(savedStudent);
    }

    @Override
    @Transactional
    public void delete(long id) {
        studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(notFound));
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.studentsToDto(students);
    }
}
