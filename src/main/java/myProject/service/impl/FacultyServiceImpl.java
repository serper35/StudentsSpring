package myProject.service.impl;

import jakarta.transaction.Transactional;
import myProject.dto.FacultyDto;
import myProject.entity.Faculty;
import myProject.mapper.FacultyMapper;
import myProject.repository.FacultyRepository;
import myProject.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepository facultyRepository;
    private FacultyMapper facultyMapper;
    private String notFound = "Faculty not found";

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyMapper facultyMapper) {
        this.facultyRepository = facultyRepository;
        this.facultyMapper = facultyMapper;
    }

    @Override
    @Transactional
    public FacultyDto add(FacultyDto facultyDto) {
        Faculty faculty = facultyMapper.facultyDtoToFaculty(facultyDto);
        Faculty savedfaculty = facultyRepository.save(faculty);

        return facultyMapper.facultyToFacultyDto(savedfaculty);
    }

    @Override
    @Transactional
    public FacultyDto update(FacultyDto facultyDto) {
        Faculty faculty = facultyMapper.facultyDtoToFaculty(facultyDto);
        facultyRepository.findById(faculty.getId()).orElseThrow(() -> new NoSuchElementException(notFound));
        Faculty savedfaculty = facultyRepository.save(faculty);

        return facultyMapper.facultyToFacultyDto(savedfaculty);
    }

    @Override
    @Transactional
    public FacultyDto get(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new NoSuchElementException(notFound));
        return facultyMapper.facultyToFacultyDto(faculty);
    }

    @Override
    @Transactional
    public void delete(long id) {
        facultyRepository.findById(id).orElseThrow(() -> new NoSuchElementException(notFound));

        facultyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<FacultyDto> getAll() {
        List<Faculty> faculties = facultyRepository.findAll();

        return facultyMapper.facultiesToFacultiesDto(faculties);
    }
}
