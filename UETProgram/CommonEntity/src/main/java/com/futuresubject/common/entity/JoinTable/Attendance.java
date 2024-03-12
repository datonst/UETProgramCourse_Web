package com.futuresubject.common.entity.JoinTable;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.futuresubject.common.entity.Entity.Program;
import com.futuresubject.common.entity.Entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid",nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programid",nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Program program;

    @Column(name ="start_date")
    private LocalDate startDate;

    @Column(name ="end_date")
    private LocalDate endDate;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Attendance that = (Attendance) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
