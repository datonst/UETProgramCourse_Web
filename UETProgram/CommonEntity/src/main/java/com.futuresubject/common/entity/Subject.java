package com.futuresubject.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
}
