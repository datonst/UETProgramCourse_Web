package com.futuresubject.common.entity.Entity;

import com.fasterxml.jackson.annotation.*;

import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Enum.ProgramType;
import com.futuresubject.common.entity.JoinTable.Attendance;
import com.futuresubject.common.entity.JoinTable.Faculty_Program;
import com.futuresubject.common.entity.JoinTable.Program_Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "courseSequence", sequenceName = "TM_course_SEQ", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSequence")
    private Integer id;

    @Column(length = 40, nullable = false)
    private String programCode;

    @Column(length = 40, nullable = false)
    private String programName;

    @Column(length = 40, nullable = false)
    private String period;

    @Column(length = 40, nullable = false)
    private Double duration;

    @Column
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    @JsonIgnore
    private ProgramType programType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultyid")
    @ToString.Exclude
    @JsonIgnore
    private Faculty faculty;

    @Column
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private LevelLanguage levelLanguage;

    @OneToMany(mappedBy = "program",orphanRemoval = true)
    @ToString.Exclude
    private Set<Attendance> attendances = new HashSet<>(); // danh sách các attendances của program

    @OneToMany(mappedBy = "program",orphanRemoval = true)
    @ToString.Exclude
    private Set<Faculty_Program> facultyPrograms = new HashSet<>(); // danh sách các facultyPrograms của program

    @OneToMany(mappedBy = "program",orphanRemoval = true)
    @ToString.Exclude
    private Set<Program_Subject> programSubjects = new HashSet<>(); // danh sách các programSubjects của program



    @Column(nullable = false)
    private Integer totalCredits;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name="course_subject",
//            joinColumns = {@JoinColumn(name = "course_id")},
//            inverseJoinColumns={@JoinColumn(name="subject_id")}
//    )
//    @ToString.Exclude
//    Set<Subject> listSubject = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name="course_rolesubject",
//            joinColumns = {@JoinColumn(name = "course_id")},
//            inverseJoinColumns={@JoinColumn(name="rolesubject_id")}
//    )
//    @ToString.Exclude
//    Set<RoleSubject> listRoleSubject = new HashSet<>();

    private Integer totalOfMandatory;
    private Integer totalOfOptional;
    private Integer totalOfOptionalReinforcement;
    private Integer totalOfPhysical;
    private Integer totalOfNationalDefense;
    private Integer totalOfAdditional;
    private Integer totalOfGraduationInternship;



    public Program(String courseCode, String courseName, Faculty faculty, Integer totalCredits, Integer totalOfMandatory, Integer totalOfOptional, Integer totalOfOptionalReinforcement, Integer totalOfPhysical, Integer totalOfNationalDefense, Integer totalOfAdditional, Integer totalOfGraduationInternship) {
        this.programCode = courseCode;
        this.programName = courseName;
        this.faculty = faculty;
        this.totalCredits = totalCredits;
        this.totalOfMandatory = totalOfMandatory;
        this.totalOfOptional = totalOfOptional;
        this.totalOfOptionalReinforcement = totalOfOptionalReinforcement;
        this.totalOfPhysical = totalOfPhysical;
        this.totalOfNationalDefense = totalOfNationalDefense;
        this.totalOfAdditional = totalOfAdditional;
        this.totalOfGraduationInternship = totalOfGraduationInternship;
    }
    //    public void addRoleSubject(RoleSubject roleSubject) {
//        this.listRoleSubject.add(roleSubject);
//    }
//    public void addSubject(Subject subject) {
//        this.listSubject.add(subject);
//    }

    public String getProgramFullCode() {
        return this.programCode + "-" + this.period;
    }
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Program program = (Program) o;
        return getId() != null && Objects.equals(getId(), program.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
