package com.futuresubject.common.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.futuresubject.common.entity.Enum.GenderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "student")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "studentId")
public class Student extends Person {
    @Id
    @Column(name = "student_id",nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentId;
    //    @Column(length = 40, nullable = false, unique = true)
//    private String name;
//    @Column(length = 40, nullable = false, unique = true)
//    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroomid")
    @ToString.Exclude
    private Classroom classroom;


    @OneToMany(mappedBy = "student",orphanRemoval = true)
    @ToString.Exclude
    private Set<ObtainCert> obtainCerts = new HashSet<>(); // danh sách các obtaincerts của student

    @OneToMany(mappedBy = "student",orphanRemoval = true)
    @ToString.Exclude
    private Set<Attendance> attendances = new HashSet<>(); // danh sách các attendances của student

    @OneToMany(mappedBy = "student",orphanRemoval = true)
    @ToString.Exclude
    private Set<MarkSubject> markSubjects = new HashSet<>(); // danh sách các markSubjects của student



    public String getClassFullName() {
        return this.classroom.getClassFullName();
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Integer getAge() {
        return super.getAge();
    }

    @Override
    public GenderType getGender() {
        return super.getGender();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setAge(Integer age) {
        super.setAge(age);
    }

    @Override
    public void setGender(GenderType gender) {
        super.setGender(gender);
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

//    public Student() {
//    }
//
//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }
//
//    public Classroom getClassroom() {
//        return classroom;
//    }
//
//    public void setClassroom(Classroom classroom) {
//        this.classroom = classroom;
//    }

//    @Column(nullable = false, unique = true, length = 45)
//    private String email;
//    @Column(nullable = false, length = 64)
//    private String password;


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "student_course",
//            joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns = {@JoinColumn(name = "course_id")}
//    )
//    @ToString.Exclude
//    Set<Course> listCourse = new HashSet<>();


//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name="student_mark",
//            joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns={@JoinColumn(name="mark_id")}
//    )
//    @ToString.Exclude
//    Set<MarkSubject> listMarkSubject = new HashSet<>();


//    public Student(String studentId) {
//        this.studentId = studentId;
//    }
//
//    public Student(String studentId, String name, String date, String classCode, String email, String password) {
//        this.studentId = studentId;
//        this.name = name;
//        this.date = date;
//        this.classCode = classCode;
//        this.email = email;
//        this.password = password;
//    }
//    public void addCourse(Course course) {
//        this.listCourse.add(course);
//    }
//
//    public void addMarkSubject(MarkSubject markSubject) {
//        this.listMarkSubject.add(markSubject);
//    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Student student = (Student) o;
        return getStudentId() != null && Objects.equals(getStudentId(), student.getStudentId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}
