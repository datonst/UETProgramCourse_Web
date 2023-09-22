package com.futuresubject.common.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(length = 40, nullable = false, unique = true)
    private String name;
    @Column(length = 40, nullable = false, unique = true)
    private String date;

    @Column(length = 40, nullable = false, unique = true)
    private String classCode;

    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    Set<Course> listCourse = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studentId")
    Set<ScoreSubject> listScore = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="prerequisite_subject",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns={@JoinColumn(name="prerequisite_id")}
    )
    Set<Subject> prerequisiteSubject = new HashSet<>();
}
