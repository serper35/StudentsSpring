package myProject.repository;

import myProject.entity.Student;
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
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    void addTest() {
        Student student = new Student();
        student.setName("Vova");
        student.setAge(10);


        Student saved = studentRepository.save(student);

        assertNotNull(saved);
        assertEquals("Vova", saved.getName());
        assertEquals(10, saved.getAge());
    }

    @Test
    @Transactional
    void getTest() {
        Student student = new Student();
        student.setName("Vova");
        student.setAge(10);

        Student saved = studentRepository.save(student);

        Optional<Student> found = studentRepository.findById(saved.getId());

        assertNotNull(found);
        assertEquals("Vova", found.get().getName());
        assertEquals(10, found.get().getAge());
    }

    @Test
    @Transactional
    void testDelete() {
        Student student = new Student();
        student.setName("Vova");
        student.setAge(10);

        Student saved = studentRepository.save(student);;

        studentRepository.deleteById(saved.getId());

        Student found = studentRepository.findById(saved.getId()).orElse(null);

        assertNull(found);
    }

    @Test
    @Transactional
    void testFindAll() {
        Student student = new Student();
        student.setName("Vova");
        student.setAge(10);

        Student student2 = new Student();
        student2.setName("Max");
        student2.setAge(15);

        Student saved = studentRepository.save(student);;
        Student saved2 = studentRepository.save(student2);;

        List<Student> students = studentRepository.findAll();

        assertTrue(students.stream().anyMatch(f -> f.getName().equals("Vova")));
        assertTrue(students.stream().anyMatch(f -> f.getName().equals("Max")));
    }
}