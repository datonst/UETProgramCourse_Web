package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Attendance;

import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface AttendanceRepository  extends CrudRepository<Attendance, Integer> {

    @Query("SELECT u  FROM Attendance AS u")
    List<Attendance> listAttendance();
    @Query("SELECT u.program  FROM Attendance AS u WHERE u.student = ?1")
    List<Program> listOfProgram(Student student);

    @Query("SELECT u.id  FROM Attendance AS u WHERE u.student.studentId = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    Integer findId(String subjectId, String programFullCode);

    @Query("SELECT u FROM Attendance AS u WHERE u.student.studentId = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    Attendance findAttendanceByStudentIdAndProgramId(String subjectId, String programFullCode);

    @Modifying
    @Query("DELETE FROM Attendance AS u WHERE u.student.studentId = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    void deleteAttendanceByStudentIdAndProgramId(String subjectId, String programFullCode);

    @Query("SELECT u.program.levelLanguage  FROM Attendance AS u WHERE u.student.studentId = ?1")
    Set<LevelLanguage> listOfLevelLanguage(String studentId);

    @Query("SELECT u.startDate  FROM Attendance AS u WHERE u.student.studentId = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    LocalDate findAttendanceDate(String subjectId, String programFullCode);
}
