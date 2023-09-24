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

//    @Column(name = "subject_id")

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
//    @JoinTable(
//            name="scores_subject",
//            joinColumns = {@JoinColumn(name = "scores_id")},
//            inverseJoinColumns={@JoinColumn(name="subject_id")}
//    )
    private Subject subjectId;

    @Column(nullable = false)
    private Double score;

}
