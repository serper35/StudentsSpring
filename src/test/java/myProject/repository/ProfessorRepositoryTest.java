package myProject.repository;

import myProject.entity.Professor;
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
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    @Transactional
    void addTest() {
        Professor professor = new Professor();
        professor.setName("Vova");


        Professor saved = professorRepository.save(professor);

        assertNotNull(saved);
        assertEquals("Vova", saved.getName());
    }

    @Test
    @Transactional
    void getTest() {
        Professor professor = new Professor();
        professor.setName("Vova");


        Professor saved = professorRepository.save(professor);

        Optional<Professor> foundProf = professorRepository.findById(saved.getId());

        assertNotNull(foundProf);
        assertEquals("Vova", foundProf.get().getName());
    }

    @Test
    @Transactional
    void testDeleteProf() {
        Professor professor = new Professor();
        professor.setName("Vova");


        Professor saved = professorRepository.save(professor);

        professorRepository.deleteById(saved.getId());

        Professor foundProf = professorRepository.findById(saved.getId()).orElse(null);

        assertNull(foundProf);
    }

    @Test
    @Transactional
    void testFindAll() {
        Professor professor = new Professor();
        professor.setName("Vova");

        Professor professor2 = new Professor();
        professor2.setName("Ko");

        Professor saved = professorRepository.save(professor);
        Professor saved2 = professorRepository.save(professor2);

        List<Professor> professors = professorRepository.findAll();

        assertTrue(professors.stream().anyMatch(f -> f.getName().equals("Vova")));
        assertTrue(professors.stream().anyMatch(f -> f.getName().equals("Ko")));
    }

}