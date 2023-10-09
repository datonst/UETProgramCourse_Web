package com.futuresubject.admin.repository;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.common.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {
    @Query("SELECT u.subjectid FROM Subject AS u")
    public List<String> listOfSubjectId();
}
