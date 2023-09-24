package com.futuresubject.common.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Score")
public class ScoreSubject {
    @Id
    @SequenceGenerator(name = "ScoresSequence", sequenceName = "TM_SCORES_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ScoresSequence")
    private Integer scoreId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student studentId;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @Column(nullable = false)
    private Double score;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ScoreSubject(Integer scoreId, Student studentId, Subject subjectId) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
}
