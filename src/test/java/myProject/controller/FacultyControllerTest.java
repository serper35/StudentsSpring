package myProject.controller;

import myProject.dto.FacultyDto;
import myProject.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class FacultyControllerTest {
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(facultyController).build();
    }

    @Test
    void testGetFaculty() throws Exception {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);
        facultyDto.setName("Faculty");
        facultyDto.setNumberOfStudents(10);
        when(facultyService.get(1L)).thenReturn(facultyDto);

        mockMvc.perform(get("/faculties/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Faculty"))
                .andExpect(jsonPath("$.numberOfStudents").value(10));

        verify(facultyService).get(1);
    }

    @Test
    void testAddFaculty() throws Exception {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName("Faculty");
        facultyDto.setNumberOfStudents(10);

        FacultyDto savedFac = new FacultyDto();
        savedFac.setName("Faculty");
        savedFac.setNumberOfStudents(10);
        savedFac.setId(1L);

        when(facultyService.add(facultyDto)).thenReturn(savedFac);

        mockMvc.perform(post("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Faculty\",\"numberOfStudents\":100}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Faculty"))
                .andExpect(jsonPath("$.numberOfStudents").value(10));

        verify(facultyService, times(1)).add(any(FacultyDto.class));
    }

    @Test
    void testUpdateFaculty() throws Exception {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName("Faculty");
        facultyDto.setNumberOfStudents(10);
        facultyDto.setId(1L);

        FacultyDto savedFac = new FacultyDto();
        savedFac.setName("Faculty");
        savedFac.setNumberOfStudents(100);
        savedFac.setId(1L);

        when(facultyService.update(facultyDto)).thenReturn(savedFac);

        mockMvc.perform(put("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Faculty\",\"numberOfStudents\":100}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Faculty"))
                .andExpect(jsonPath("$.numberOfStudents").value(100));

        verify(facultyService, times(1)).update(any(FacultyDto.class));
    }

    @Test
    void testDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculties/{id}", 1))
                .andExpect(status().isOk());

        verify(facultyService).delete(1);
    }

    @Test
    void testGetAllFaculties() throws Exception {
        List<FacultyDto> facultyDtoList = new ArrayList<>();

        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(1L);
        facultyDto.setName("Faculty");
        facultyDto.setNumberOfStudents(10);
        facultyDtoList.add(facultyDto);
        when(facultyService.getAll()).thenReturn(facultyDtoList);

        mockMvc.perform(get("/faculties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Faculty"))
                .andExpect(jsonPath("$[0].numberOfStudents").value(10));

        verify(facultyService).getAll();
    }
}