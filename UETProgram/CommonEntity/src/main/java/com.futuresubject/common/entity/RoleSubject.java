package com.futuresubject.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "roles")
public class RoleSubject {
    @Id
    @SequenceGenerator(name = "RolesSequence", sequenceName = "TM_ROLES_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RolesSequence")
    private Integer id;

    @Column(name = "course_id")
    private String courseId;




    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="roles_prerequisite",
            joinColumns = {@JoinColumn(name = "roles_id")},
            inverseJoinColumns={@JoinColumn(name="subject_id")}
    )
    Set<Subject> prerequisiteSubject = new HashSet<>();
    @Column(length = 40, nullable = false)
    private String typeRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Set<Subject> getPrerequisiteSubject() {
        return prerequisiteSubject;
    }

    public void setPrerequisiteSubject(Set<Subject> prerequisiteSubject) {
        this.prerequisiteSubject = prerequisiteSubject;
    }

    public String getTypeRole() {
        return typeRole;
    }

    public void setTypeRole(String typeRole) {
        this.typeRole = typeRole;
    }

    public RoleSubject(Integer id, String courseId, Subject subjectId, String typeRole) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.typeRole = typeRole;
    }
}
