package com.futuresubject.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {
    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(length = 40, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer totalCredits;
    @Column(length = 40, nullable = false, unique = true)
    private String faculty;
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="course_subject",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns={@JoinColumn(name="subject_id")}
    )
    Set<Subject> listSubject = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="course_roles",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns={@JoinColumn(name="roles_id")}
    )
    Set<RoleSubject> listRoleSubject = new HashSet<>();
    private Integer totalOfMandatory;
    private Integer totalOfOptional;
    private Integer totalOfOptionalReinforcement;
    private Integer totalOfPhysical;
    private Integer totalOfNationalDefense;
    private Integer totalOfAdditional;
    private Integer totalOfGraduationInternship;

}
