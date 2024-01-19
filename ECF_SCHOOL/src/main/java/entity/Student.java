package entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_Student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private int id;

    @Column(name = "st_last_name", nullable = false)
    @Size(min = 3)
    private String lastName;

    @Column(name = "st_first_name", nullable = false)
    private String firstName;

    @Column(name = "st_email")
    @Pattern(regexp = ".+@gmail\\.com", message = "L'adresse e-mail doit être de la forme '...@gmail.com'")
    private String email;

    @Column(name = "st_date_birth")
    private LocalDate dateOfBirth;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "c_id", nullable = false)
    private ClassSchool aClassSchool;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", aClass=" + aClassSchool +
                '}';
    }
}

