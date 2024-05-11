package myProject.repository;

import myProject.entity.Faculty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfigTest.class)
@Testcontainers
class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    @Transactional
    void addTest() {
        Faculty faculty = new Faculty();
        faculty.setName("math");
        faculty.setNumberOfStudents(10);


        Faculty saved = facultyRepository.save(faculty);

        assertNotNull(saved);
        assertEquals("math", saved.getName());
        assertEquals(10, saved.getNumberOfStudents());
    }

    @Test
    @Transactional
    void getTest() {
        Faculty faculty = new Faculty();
        faculty.setName("math");
        faculty.setNumberOfStudents(10);


        Faculty saved = facultyRepository.save(faculty);

        Optional<Faculty> foundFaculty = facultyRepository.findById(saved.getId());

        assertNotNull(foundFaculty);
        assertEquals("math", foundFaculty.get().getName());
        assertEquals(10, foundFaculty.get().getNumberOfStudents());
    }

    @Test
    @Transactional
    void testDeleteFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Biology");
        faculty.setNumberOfStudents(100);

        Faculty savedFaculty = facultyRepository.save(faculty);

        facultyRepository.deleteById(savedFaculty.getId());

        Faculty foundFaculty = facultyRepository.findById(savedFaculty.getId()).orElse(null);

        assertNull(foundFaculty);
    }

    @Test
    @Transactional
    void testFindAllFaculties() {
        Faculty faculty1 = new Faculty();
        faculty1.setName("Physics");
        faculty1.setNumberOfStudents(200);

        Faculty faculty2 = new Faculty();
        faculty2.setName("Chemistry");
        faculty2.setNumberOfStudents(150);

        facultyRepository.save(faculty1);
        facultyRepository.save(faculty2);

        List<Faculty> faculties = facultyRepository.findAll();

        assertTrue(faculties.stream().anyMatch(f -> f.getName().equals("Physics") && f.getNumberOfStudents() == 200));
        assertTrue(faculties.stream().anyMatch(f -> f.getName().equals("Chemistry") && f.getNumberOfStudents() == 150));
    }
}