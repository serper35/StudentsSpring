package myProject.mapper;

import myProject.dto.ProfessorDto;
import myProject.entity.Professor;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

    Professor professorDtoToProfessor(ProfessorDto professor);
    @Named("professorsDto")
    ProfessorDto professorToProfessorDto(Professor professor);

    @Named("professorsDto")
    @IterableMapping(qualifiedByName = "professorsDto")
    default List<ProfessorDto> professorsDto(List<Professor> professors) {
        return professors.stream()
                .map(this::professorToProfessorDto)
                .collect(Collectors.toList());
    }
}
