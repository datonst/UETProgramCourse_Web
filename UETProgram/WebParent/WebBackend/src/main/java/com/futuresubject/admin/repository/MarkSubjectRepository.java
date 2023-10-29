package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
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
}
