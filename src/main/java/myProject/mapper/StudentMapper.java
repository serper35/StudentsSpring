package myProject.mapper;

import myProject.dto.StudentDto;
import myProject.entity.Student;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student studentDtoToStudent(StudentDto studentDto);

    @Named("studentsToDto")
    StudentDto studentToStudentDto(Student student);


    @Named("studentsToDto")
    @IterableMapping(qualifiedByName = "studentsToDto")
    default List<StudentDto> studentsToDto(List<Student> students){
        return students.stream()
                .map(this::studentToStudentDto)
                .collect(Collectors.toList());
    }
}
