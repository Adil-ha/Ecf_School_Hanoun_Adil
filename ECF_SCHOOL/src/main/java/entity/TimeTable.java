package entity;

import enumSchool.SchoolDayOfWeek;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "T_Time_Table")
@Data
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ti_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ti_day_of_week")
    private SchoolDayOfWeek dayOfWeek;

    @Column(name = "ti_start_time")
    private LocalTime startTime;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="T_Subject_Time_Table",
        joinColumns = @JoinColumn(name="ti_id"),
        inverseJoinColumns = @JoinColumn(name = "s_id"))
    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "c_id", nullable = false)
    private ClassSchool aClassSchool;

    @Override
    public String toString() {
        return "TimeTable{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", aClass=" + aClassSchool +
                '}';
    }
}