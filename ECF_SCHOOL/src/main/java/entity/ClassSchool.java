package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_Class")
@Data
public class ClassSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_level")
    private String level;

    @ManyToOne
    @JoinColumn(name = "d_id", nullable = false)
    private Department department;

    @ManyToMany(mappedBy = "classSchools")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "aClassSchool", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "aClassSchool", cascade = CascadeType.ALL )
    private List<TimeTable> timeTables;

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", department=" + department +
                '}';
    }
}
