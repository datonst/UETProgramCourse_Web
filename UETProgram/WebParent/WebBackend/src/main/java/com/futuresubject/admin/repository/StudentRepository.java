package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT u.studentId FROM Student AS u")
    public List<String> listOfStudentId();

    Long countByStudentId(String studentid);

    @Query("SELECT u.studentId FROM Student AS u WHERE u.classroom.cohort = ?1")
    List<String> countByCohort(String cohort);

    @Query("SELECT u.studentId FROM Student AS u " +
            "INNER JOIN Attendance AS a ON u.studentId = a.student.studentId " +
            "WHERE u.classroom.cohort = ?1 AND concat(a.program.programCode,'-',a.program.period) = ?2")
    List<String> countByCohortAndProgram(String cohort, String programFullCode);



}
