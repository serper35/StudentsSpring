package myProject.mapper;

import myProject.dto.FacultyDto;
import myProject.entity.Faculty;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfessorMapper.class, StudentMapper.class})
public interface FacultyMapper {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    @Named("facultyToFacultyDto")
    FacultyDto facultyToFacultyDto(Faculty group);

    Faculty facultyDtoToFaculty(FacultyDto facultyDto);

    List<Faculty> facultiesDtoToFaculties(List<FacultyDto> facultiesDto);

    @IterableMapping(qualifiedByName = "facultyToFacultyDto")
    List<FacultyDto> facultiesToFacultiesDto(List<Faculty> faculties);
}
