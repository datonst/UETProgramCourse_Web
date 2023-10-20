package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkSubjectRepository extends CrudRepository<MarkSubject, Integer> {
    @Query("SELECT u  FROM MarkSubject AS u " +
            "INNER JOIN Program AS c ON concat(c.programCode,'-',c.period) =?2" +
            " WHERE u.student.studentId =?1")
    List<MarkSubject> findMarkList(String studentId, String programFullCode);


}
