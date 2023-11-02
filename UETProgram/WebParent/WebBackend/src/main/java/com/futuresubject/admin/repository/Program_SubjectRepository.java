package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Program_Subject;
import com.futuresubject.common.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Program_SubjectRepository extends CrudRepository<Program_Subject, Integer> {
    @Query("SELECT u.subject  FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) =?1")
    List<Subject> findAllSubject(String programFullCode);

    @Query("SELECT u.subject  FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) = ?2 " +
            " AND u.subject NOT IN (SELECT a.subject from MarkSubject AS a WHERE a.student.studentId= ?1 AND a.subject IS NOT NULL)")
    List<Subject> findAllSubjectUnfinished( String studentId, String programFullCode);

    @Query("SELECT u.subject  FROM Program_Subject AS u " +
            " WHERE concat(u.program.programCode,'-',u.program.period) =?2 AND u.subject.roleType = ?3 " +
            " AND u.subject NOT IN (SELECT a.subject from MarkSubject AS a WHERE a.student.studentId= ?1 AND a.subject IS NOT NULL)")
    List<Subject> findAllSubjectUnfinishedByRoleType(String studentId, String programFullCode, RoleType roleType);

    @Query("SELECT u.id FROM Program_Subject AS u " +
            " WHERE u.subject.subjectid = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    Integer findId(String subjectid, String programFullCode);


}
