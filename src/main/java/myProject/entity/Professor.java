package myProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "professors", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Faculty> faculties = new ArrayList<>();

    public Professor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Professor() {
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
        Professor professor = (Professor) object;
        return id == professor.id && Objects.equals(name, professor.name) && Objects.equals(faculties, professor.faculties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, faculties);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculties=" + faculties +
                '}';
    }
}
