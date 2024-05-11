package myProject.service;

import myProject.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    FacultyDto add(FacultyDto facultyDto);

    FacultyDto update(FacultyDto facultyDto);

    FacultyDto get(long id);

    void delete(long id);

    List<FacultyDto> getAll();
}
