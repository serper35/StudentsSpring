package myProject.controller;

import myProject.dto.ProfessorDto;
import myProject.service.ProfessorService;
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
class ProfessorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }

    @Test
    void testGetProfessor() throws Exception {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(1L);
        professorDto.setName("Vova");
        when(professorService.get(1L)).thenReturn(professorDto);

        mockMvc.perform(get("/professors/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vova"));

        verify(professorService).get(1);
    }

    @Test
    void testAddProfessor() throws Exception {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName("Vova");

        ProfessorDto savedProf = new ProfessorDto();
        savedProf.setId(1L);
        savedProf.setName("Vova");

        when(professorService.add(professorDto)).thenReturn(savedProf);

        mockMvc.perform(post("/professors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Vova\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vova"));

        verify(professorService, times(1)).add(any(ProfessorDto.class));
    }

    @Test
    void testUpdateProfessor() throws Exception {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName("Vova");
        professorDto.setId(1L);

        ProfessorDto savedProf = new ProfessorDto();
        savedProf.setId(1L);
        savedProf.setName("Prof");

        when(professorService.update(professorDto)).thenReturn(savedProf);

        mockMvc.perform(put("/professors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Vova\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Prof"));

        verify(professorService, times(1)).update(any(ProfessorDto.class));
    }

    @Test
    void testDeleteProfessor() throws Exception {
        mockMvc.perform(delete("/professors/{id}", 1))
                .andExpect(status().isOk());

        verify(professorService).delete(1);
    }

    @Test
    void testGetAllProfessors() throws Exception {
        List<ProfessorDto> professorDtoList = new ArrayList<>();

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(1L);
        professorDto.setName("Vova");
        professorDtoList.add(professorDto);
        when(professorService.getAll()).thenReturn(professorDtoList);

        mockMvc.perform(get("/professors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Vova"));

        verify(professorService).getAll();
    }
}