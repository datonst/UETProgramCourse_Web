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
    @Column(length = 40, nullable = false, unique = true)
    private String faculty;
    @Column(nullable = false)
    private Integer totalCredits;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="course_subject",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns={@JoinColumn(name="subject_id")}
    )
    Set<Subject> listSubject = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="course_rolesubject",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns={@JoinColumn(name="rolesubject_id")}
    )
    Set<RoleSubject> listRoleSubject = new HashSet<>();

    private Integer totalOfMandatory;
    private Integer totalOfOptional;
    private Integer totalOfOptionalReinforcement;
    private Integer totalOfPhysical;
    private Integer totalOfNationalDefense;
    private Integer totalOfAdditional;
    private Integer totalOfGraduationInternship;

    public Course() {

    }

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public Course(String courseId, String name, String faculty, Integer totalCredits, Integer totalOfMandatory, Integer totalOfOptional, Integer totalOfOptionalReinforcement, Integer totalOfPhysical, Integer totalOfNationalDefense, Integer totalOfAdditional, Integer totalOfGraduationInternship) {
        this.courseId = courseId;
        this.name = name;
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
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


    public Set<Subject> getListSubject() {
        return listSubject;
    }

    public void setListSubject(Set<Subject> listSubject) {
        this.listSubject = listSubject;
    }

    public Set<RoleSubject> getListRoleSubject() {
        return listRoleSubject;
    }

    public void setListRoleSubject(Set<RoleSubject> listRoleSubject) {
        this.listRoleSubject = listRoleSubject;
    }

    public Integer getTotalOfMandatory() {
        return totalOfMandatory;
    }

    public void setTotalOfMandatory(Integer totalOfMandatory) {
        this.totalOfMandatory = totalOfMandatory;
    }

    public Integer getTotalOfOptional() {
        return totalOfOptional;
    }

    public void setTotalOfOptional(Integer totalOfOptional) {
        this.totalOfOptional = totalOfOptional;
    }

    public Integer getTotalOfOptionalReinforcement() {
        return totalOfOptionalReinforcement;
    }

    public void setTotalOfOptionalReinforcement(Integer totalOfOptionalReinforcement) {
        this.totalOfOptionalReinforcement = totalOfOptionalReinforcement;
    }

    public Integer getTotalOfPhysical() {
        return totalOfPhysical;
    }

    public void setTotalOfPhysical(Integer totalOfPhysical) {
        this.totalOfPhysical = totalOfPhysical;
    }

    public Integer getTotalOfNationalDefense() {
        return totalOfNationalDefense;
    }

    public void setTotalOfNationalDefense(Integer totalOfNationalDefense) {
        this.totalOfNationalDefense = totalOfNationalDefense;
    }

    public Integer getTotalOfAdditional() {
        return totalOfAdditional;
    }

    public void setTotalOfAdditional(Integer totalOfAdditional) {
        this.totalOfAdditional = totalOfAdditional;
    }

    public Integer getTotalOfGraduationInternship() {
        return totalOfGraduationInternship;
    }

    public void setTotalOfGraduationInternship(Integer totalOfGraduationInternship) {
        this.totalOfGraduationInternship = totalOfGraduationInternship;
    }

    public void addRoleSubject(RoleSubject roleSubject) {
        this.listRoleSubject.add(roleSubject);
    }
    public void addSubject(Subject subject) {
        this.listSubject.add(subject);
    }
}
