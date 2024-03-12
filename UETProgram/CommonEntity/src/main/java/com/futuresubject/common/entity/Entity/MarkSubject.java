package com.futuresubject.common.entity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="marksubject")
public class MarkSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "marksubjectSequence", sequenceName = "TM_marksubject_SEQ", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marksubjectSequence")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id",nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Subject subject;

    @Column(nullable = false)
    private Double mark;


    public MarkSubject(Student student, Subject subject, Double mark) {
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public static class CourseNotFoundException extends Throwable {
        public CourseNotFoundException(String message) {
            super(message);
        }
    }


    @PreRemove
    public void removeEverything(){
        this.student = null;
        this.subject = null;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MarkSubject that = (MarkSubject) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
