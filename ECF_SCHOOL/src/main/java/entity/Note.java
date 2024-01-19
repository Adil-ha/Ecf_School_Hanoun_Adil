package entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "T_Note")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id")
    private int id;

    @Column(name = "n_value", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "20.0", inclusive = true)
    private float value;

    @Column(name = "n_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", value=" + value +
                ", comment='" + comment + '\'' +
                '}';
    }
}
