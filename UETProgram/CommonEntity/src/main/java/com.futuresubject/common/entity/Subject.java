package com.futuresubject.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id")
    private String subjectId;

    @Column(length = 40, nullable = false, unique = true)
    private String nameSubject;
    @Column(nullable = false)
    private Integer credit;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "subjectId")
//    private ScoreSubject scoreSubject;
//
//
//    @OneToOne(mappedBy = "subjectId")
//    private RoleSubject roleSubject;


    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Subject(String subjectId, String nameSubject, Integer credit) {
        this.subjectId = subjectId;
        this.nameSubject = nameSubject;
        this.credit = credit;
    }
}
