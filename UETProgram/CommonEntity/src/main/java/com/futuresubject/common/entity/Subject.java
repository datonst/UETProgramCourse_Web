package com.futuresubject.common.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.futuresubject.common.entity.Enum.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "subject")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "subjectid")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Subject {

//    @SequenceGenerator(name = "courseSequence", sequenceName = "TM_course_SEQ", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSequence")
//    private Integer id;
    @Id
    @Column(name = "subject_id",nullable = false)
//    @Column(length = 40, nullable = false, unique = true)
    private String subjectid;

    @Column(length = 100, nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private Integer credit;

    @ManyToMany
    @JoinTable(
            name="subject_prerequisite",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns={@JoinColumn(name="prerequisite_id")}
    )
    @ToString.Exclude
    Set<Subject> prerequisiteSubject = new HashSet<>();


    @ManyToMany(mappedBy = "prerequisiteSubject")
    @ToString.Exclude
    private Set<Subject> referenceList = new HashSet<>(); // danh sách các subject mà có học phần tiên quyết là subject này


    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Subject() {
    }

    public void addPrerequisite(Subject subject) {
        this.prerequisiteSubject.add(subject);
        subject.getReferenceList().add(this);
    }

    public void removePrerequisite(Subject subject) {
        this.prerequisiteSubject.remove(subject);
        subject.getReferenceList().remove(this);
    }

    @PreRemove // tự động callback và gọi hàm này nếu xoá entity
    public void removeReferenceList() {
        if (referenceList!=null) {
            for (Subject subject : referenceList) { // remove all non-owner
                subject.getPrerequisiteSubject().remove(this);
            }
        }
        if (prerequisiteSubject!=null) {
            for (Subject subject : prerequisiteSubject) {  // remove all owner
                removePrerequisite(subject);
            }
        }
    }

    public List<String> getPrerequisiteSubjectId() {
        return prerequisiteSubject.stream()
                .map(s -> s.subjectid)
                .collect(Collectors.toList());
    }
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "subjectId")
//    private Mark markSubject;
//
//
//    @OneToOne(mappedBy = "subjectId")
//    private RoleSubject roleSubject;


//    public Subject(String subjectId) {
//        this.subjectId = subjectId;
//    }
//    public Subject(String subjectId, String nameSubject, Integer credit) {
//        this.subjectId = subjectId;
//        this.nameSubject = nameSubject;
//        this.credit = credit;
//    }

//    @Override
//    public final boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
//        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
//        if (thisEffectiveClass != oEffectiveClass) return false;
//        Subject subject = (Subject) o;
//        return getSubjectId() != null && Objects.equals(getSubjectId(), subject.getSubjectId());
//    }

//    @Override
//    public final int hashCode() {
//        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
//    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Subject subject = (Subject) o;
        return getSubjectid() != null && Objects.equals(getSubjectid(), subject.getSubjectid());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}
