package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    @Query("SELECT u.subjectid FROM Subject AS u")
    public List<String> listOfSubjectId();

    public Long countBySubjectid(String subjectid); // count id -- Spring Data JPA làm sẵn công đoạn Query rồi ( gọi là  Derived Count Query )

//    @Query("SELECT u.subjectid FROM Subject AS u " +
//            " WHERE u.subjectName = ?1 ")
//    List<Subject> findSubjectBySubjectName(String subjectName);

    @Query("SELECT u.prerequisiteSubject FROM Subject AS u " +
            " WHERE u.subjectName = ?1 ")
    Set<Subject> findPrerequisiteSubjectBySubjectName(String subjectName);
}
