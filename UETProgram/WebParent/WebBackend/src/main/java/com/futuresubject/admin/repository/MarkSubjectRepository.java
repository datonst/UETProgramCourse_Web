package com.futuresubject.admin.repository;


import com.futuresubject.admin.dto.search.MarkDto;
import com.futuresubject.admin.dto.search.SearchMark;
import com.futuresubject.admin.dto.search.SubjectInfoDto;
import com.futuresubject.common.entity.MarkSubject;

import com.futuresubject.common.entity.Enum.RoleType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MarkSubjectRepository extends CrudRepository<MarkSubject, Integer> {

    @Query("SELECT u  FROM MarkSubject AS u " +
            "INNER JOIN Program AS c ON concat(c.programCode,'-',c.period) =?2" +
            " WHERE u.student.studentId =?1")
    List<MarkSubject> findMarkList(String studentId, String programFullCode);

    @Modifying
    @Query("DELETE FROM MarkSubject AS u " +
            " WHERE u.student.studentId = ?1 AND u.subject.subjectid = ?2")
    void deleteMark(String studentId, String subjectId);

    @Query("SELECT u FROM MarkSubject AS u " +
            " WHERE u.student.studentId = ?1 AND u.subject.subjectid = ?2")
    MarkSubject findMarkSubject(String studentId, String subjectId);
    @Query("SELECT u.id FROM MarkSubject AS u " +
            " WHERE u.student.studentId = ?1 AND u.subject.subjectid = ?2")
    Integer findId(String studentId, String subjectId);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE MarkSubject AS u SET u.mark = ?3 " +
            " WHERE u.student.studentId = ?1 AND u.subject.subjectid = ?2")
    void updateMark(String studentId, String subjectId, Double mark);


    @Query("SELECT sum (u.mark * u.subject.credit) FROM MarkSubject AS u " +
            " INNER JOIN Program_Subject AS t ON t.program.id = ?2 " +
            " WHERE   u.student.studentId = ?1 AND t.subject = u.subject")
    Double sumMark(String studentId, Integer programId);


    @Query(value = "SELECT " +
            "new com.futuresubject.admin.dto.search.SubjectInfoDto" +
            "(u.subject.subjectName,u.subject.credit,u.subject.roleType,u.mark)" +
            " FROM MarkSubject AS u " +
            " INNER JOIN Program_Subject AS t ON u.subject.subjectid = t.subject.subjectid " +
            " WHERE u.student.studentId = ?1 AND concat(t.program.programCode,'-',t.program.period) = ?2 ",nativeQuery = false)
    List<SubjectInfoDto> getALlMarkByStudentAndProgram(String studentId, String programFullCode);

    @Query(value = "SELECT " +
            "new com.futuresubject.admin.dto.search.SubjectInfoDto" +
            "(u.subject.subjectName,u.subject.credit,u.subject.roleType,u.mark)" +
            " FROM MarkSubject AS u " +
            " INNER JOIN Program_Subject AS t ON u.subject.subjectid = t.subject.subjectid " +
            " WHERE u.student.studentId = ?1 " +
            " AND concat(t.program.programCode,'-',t.program.period) = ?2 " +
            " AND u.subject.roleType = ?3 ",nativeQuery = false)
    List<SubjectInfoDto> getALlMarkByRoleType(String mssv, String programFullCode, RoleType roleType);


    // Phiên bản thứ 1 - non-native
    @Query(value = "SELECT new com.futuresubject.admin.dto.search.SearchMark(u.student.studentId,t.program.programName,sum(u.mark)) FROM MarkSubject AS u " +
          " INNER JOIN Program_Subject AS t ON u.subject.subjectid = t.subject.subjectid " +
            " GROUP BY u.student.studentId,t.program.programName",nativeQuery = false)
    List<SearchMark> val();

    // Phiên bản thứ 2: non-native

//    @Query(value = "SELECT u.student.studentId AS studentId,t.program.programName AS programName,sum(u.mark) AS sumMark FROM MarkSubject AS u " +
//          " INNER JOIN Program_Subject AS t ON u.subject.subjectid = t.subject.subjectid " +
//            " GROUP BY u.student.studentId,t.program.programName",nativeQuery = false)
//    List<Object[]> val();    --> chú ý: cần ép kiểu khi dùng


    // Phiên bản thứ 3: native
//    @Query(value = "SELECT u.student_id AS studentId,t.program_id AS programId,sum(u.mark) AS sumMark FROM marksubject AS u " +
//          " INNER JOIN program_subject AS t ON u.subject_id = t.subject_id " +
//            " GROUP BY u.student_id,t.program_id",nativeQuery = true)
//    List<SearchMas> val();








//    @Query("SELECT sum(u.mark)/count(*) FROM MarkSubject AS u " +
//            " INNER JOIN Program_Subject AS t ON u.subject.subjectid = t.subject.subjectid " +
//            " WHERE u.student.studentId = ?1 AND t.program.id = ?2 AND u.subject.roleType= ?3" +
//            " GROUP BY u.student.studentId ")
//    Double getAverageMarkObey(String studentId,String programId,RoleType roleType);


    @Query("SELECT new com.futuresubject.admin.dto.search.MarkDto(u.mark,u.subject.credit)  FROM MarkSubject AS u " +
            " INNER JOIN Program_Subject AS t ON u.subject.subjectid = t.subject.subjectid " +
            " WHERE u.student.studentId = ?1 AND t.program.id = ?2 AND u.subject.roleType =  ?3" )
    List<MarkDto> getMarkByRole(String studentId, Integer programId, RoleType roleType);


}
