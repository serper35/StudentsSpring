package myProject.dto;

import myProject.entity.Professor;
import myProject.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacultyDto {

    private long id;
    private String name;
    private int numberOfStudents;
    private List<Professor> professors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public FacultyDto(long id, String name, int numberOfStudents, List<Professor> professors, List<Student> students) {
        this.id = id;
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.professors = professors;
        this.students = students;
    }

    public FacultyDto() {
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

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FacultyDto facultyDto = (FacultyDto) object;
        return id == facultyDto.id && Objects.equals(name, facultyDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FacultyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", professors=" + professors +
                ", students=" + students +
                '}';
    }
}
