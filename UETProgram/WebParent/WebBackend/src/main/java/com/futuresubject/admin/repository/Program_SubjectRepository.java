package com.futuresubject.admin.repository;

import com.futuresubject.admin.dto.search.SubjectInfoDto;
import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Program_Subject;
import com.futuresubject.common.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Program_SubjectRepository extends JpaRepository<Program_Subject, Integer> {
    @Query("SELECT " +
            "new com.futuresubject.admin.dto.search.SubjectInfoDto" +
            "(u.subject.subjectName,u.subject.credit,u.roleType) FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) =?1")
    List<SubjectInfoDto> findAllSubject(String programFullCode);

    @Query("SELECT " +
            "new com.futuresubject.admin.dto.search.SubjectInfoDto" +
            "(u.subject.subjectName,u.subject.credit,u.roleType)  FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) = ?2 " +
            " AND u.subject NOT IN (SELECT a.subject from MarkSubject AS a WHERE a.student.studentId= ?1 AND a.subject IS NOT NULL)")
    List<SubjectInfoDto> findAllSubjectUnfinished(String studentId, String programFullCode);

    @Query("SELECT " +
            "new com.futuresubject.admin.dto.search.SubjectInfoDto" +
            "(u.subject.subjectName,u.subject.credit, u.roleType)  FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) =?2 AND u.roleType = ?3 " +
            " AND u.subject NOT IN (SELECT a.subject from MarkSubject AS a WHERE a.student.studentId= ?1 AND a.subject IS NOT NULL)")
    List<SubjectInfoDto> findAllSubjectUnfinishedByRoleType(String studentId, String programFullCode, RoleType roleType);

    @Query("SELECT u.id FROM Program_Subject AS u " +
            " WHERE u.subject.subjectid = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    Integer findId(String subjectid, String programFullCode);

    @Query("SELECT " +
            "new com.futuresubject.admin.dto.search.SubjectInfoDto" +
            "(u.subject.subjectName,u.subject.credit,u.roleType) FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) =?1 AND u.roleType = ?2")
    List<SubjectInfoDto> findAllSubjectByRoleType(String programFullCode, RoleType roleType);
}
