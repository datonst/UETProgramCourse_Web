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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="student_scores",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns={@JoinColumn(name="scores_id")}
    )
    Set<ScoreSubject> listScore = new HashSet<>();

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(Set<Course> listCourse) {
        this.listCourse = listCourse;
    }

    public Set<ScoreSubject> getListScore() {
        return listScore;
    }

    public void setListScore(Set<ScoreSubject> listScore) {
        this.listScore = listScore;
    }

    public Student(String studentId, String name, String date, String classCode, String email, String password) {
        this.studentId = studentId;
        this.name = name;
        this.date = date;
        this.classCode = classCode;
        this.email = email;
        this.password = password;
    }
}
