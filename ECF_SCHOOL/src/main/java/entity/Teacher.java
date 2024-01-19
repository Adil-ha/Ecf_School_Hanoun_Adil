package entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "T_Teacher")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_matricule")
    private int matricule;

    @Column(name = "t_last_name", nullable = false)
    @Size(min = 3)
    private String lastName;

    @Column(name = "t_first_name", nullable = false)
    private String firstName;

    @Column(name = "t_age", nullable = false)
    @Min(18)
    private int age;

    @Column(name = "t_grade")
    private String grade;

    @Column(name = "t_head_teacher")
    private boolean headTeacher;


    @ManyToOne
    @JoinColumn(name = "d_id", nullable = false)
    private Department department;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "T_Teacher_Subject",
            joinColumns = @JoinColumn(name = "t_matricule"),
            inverseJoinColumns = @JoinColumn(name = "s_id")
    )
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "T_Teacher_Class",
            joinColumns = @JoinColumn(name = "t_matricule"),
            inverseJoinColumns = @JoinColumn(name = "c_id")
    )
    private List<ClassSchool> classSchools = new ArrayList<>();

    @Override
    public String toString() {
        return "Teacher{" +
                "matricule=" + matricule +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", headTeacher=" + headTeacher +
                ", department=" + department +
                '}';
    }
}
