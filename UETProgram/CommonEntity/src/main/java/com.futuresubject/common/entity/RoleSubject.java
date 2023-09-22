package com.futuresubject.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "roles")
public class RoleSubject {
    @Id
    @SequenceGenerator(name = "PositionsSequence", sequenceName = "TM_POSITIONS_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PositionsSequence")
    private Integer id;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(length = 40, nullable = false)
    private String typeRole;
}
