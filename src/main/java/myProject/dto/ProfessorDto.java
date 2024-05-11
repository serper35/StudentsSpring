package myProject.dto;

import myProject.entity.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfessorDto {
    private long id;
    private String name;
    private List<Faculty> faculties = new ArrayList<>();

    public ProfessorDto(long id, String name, List<Faculty> faculties) {
        this.id = id;
        this.name = name;
        this.faculties = faculties;
    }

    public ProfessorDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProfessorDto that = (ProfessorDto) object;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ProfessorDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculties=" + faculties +
                '}';
    }
}
