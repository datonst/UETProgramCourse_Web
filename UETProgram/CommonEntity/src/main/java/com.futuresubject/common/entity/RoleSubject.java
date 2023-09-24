package com.futuresubject.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "rolesubject")
public class RoleSubject {
    @Id
    @SequenceGenerator(name = "RoleSubjectSequence", sequenceName = "TM_ROLES_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoleSubjectSequence")
    private Integer id;

//    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listRoleSubject")
//    @JoinColumn(name = "course_id")
////    @Column(name = "course_id")
//    Set<Course> courseId = new HashSet<>();




    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="rolesubject_prerequisite",
            joinColumns = {@JoinColumn(name = "rolesubject_id")},
            inverseJoinColumns={@JoinColumn(name="subject_id")}
    )
    Set<Subject> prerequisiteSubject = new HashSet<>();
    @Column(length = 40, nullable = false)
    private String typeRole;



    public RoleSubject() {

    }

    public RoleSubject(Subject subjectId, String typeRole) {
        this.subjectId = subjectId;
        this.typeRole = typeRole;
    }

    public RoleSubject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


}
