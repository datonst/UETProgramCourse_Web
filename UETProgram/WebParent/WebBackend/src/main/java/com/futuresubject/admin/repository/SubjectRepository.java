package com.futuresubject.admin.repository;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.common.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    @Query("SELECT u.subjectid FROM Subject AS u")
    public List<String> listOfSubjectId();

    public Long countBySubjectid(String subjectid); // count id -- Spring Data JPA làm sẵn công đoạn Query rồi ( gọi là  Derived Count Query )



}
