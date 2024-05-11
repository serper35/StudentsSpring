package myProject.controller;

import myProject.dto.StudentDto;
import myProject.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testGetStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);
        studentDto.setName("Vova");
        studentDto.setAge(20);

        when(studentService.get(1L)).thenReturn(studentDto);

        mockMvc.perform(get("/students/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vova"))
                .andExpect(jsonPath("$.age").value(20));

        verify(studentService).get(1);
    }

    @Test
    void testAddStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setName("Vova");
        studentDto.setAge(20);

        StudentDto savedStud = new StudentDto();
        savedStud.setName("Vova");
        savedStud.setAge(20);
        savedStud.setId(1L);

        when(studentService.add(studentDto)).thenReturn(savedStud);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Vova\",\"age\":20}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vova"))
                .andExpect(jsonPath("$.age").value(20));

        verify(studentService, times(1)).add(any(StudentDto.class));
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setName("Vova");
        studentDto.setAge(20);
        studentDto.setId(1L);

        StudentDto savedStud = new StudentDto();
        savedStud.setName("Vova");
        savedStud.setAge(200);
        savedStud.setId(1L);

        when(studentService.update(studentDto)).thenReturn(savedStud);

        mockMvc.perform(put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Vova\",\"age\":20}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vova"))
                .andExpect(jsonPath("$.age").value(200));

        verify(studentService, times(1)).update(any(StudentDto.class));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/{id}", 1))
                .andExpect(status().isOk());

        verify(studentService).delete(1);
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<StudentDto> studentDtoList = new ArrayList<>();

        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);
        studentDto.setName("Vova");
        studentDto.setAge(20);
        studentDtoList.add(studentDto);
        when(studentService.getAll()).thenReturn(studentDtoList);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Vova"))
                .andExpect(jsonPath("$[0].age").value(20));

        verify(studentService).getAll();
    }
}