package com.futuresubject.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name="marksubject")
public class MarkSubject {
    @Id
    @SequenceGenerator(name = "marksubjectSequence", sequenceName = "TM_marksubject_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marksubjectSequence")
    private Integer markSubjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student studentId;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @Column(nullable = false)
    private Double mark;

    public MarkSubject() {
    }

    public MarkSubject(Student studentId, Subject subjectId, Double mark) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.mark = mark;
    }

    public Integer getMarkSubjectId() {
        return markSubjectId;
    }

    public void setMarkSubjectId(Integer markSubjectId) {
        this.markSubjectId = markSubjectId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
