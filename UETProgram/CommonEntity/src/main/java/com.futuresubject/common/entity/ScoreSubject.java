package com.futuresubject.common.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Score")
public class ScoreSubject {
    @Id
    @SequenceGenerator(name = "PositionsSequence", sequenceName = "TM_POSITIONS_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PositionsSequence")
    private Integer scoreId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student studentId;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(nullable = false)
    private Double score;

}
