package com.futuresubject.admin.repository;


import com.futuresubject.common.entity.Enum.CertificateType;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.ObtainCert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ObtainCertRepository extends CrudRepository<ObtainCert, Integer> {
    @Query("SELECT u FROM ObtainCert AS u")
    List<ObtainCert> listObtainCert();

    @Query("SELECT u FROM ObtainCert AS u WHERE u.student.studentId = ?1 ")
    List<ObtainCert> findByStudentId(String studentId);

    @Query("SELECT u  FROM ObtainCert AS u WHERE u.student.studentId = ?1 AND u.certificateType = ?2")
    ObtainCert findByStudentIdAndLevelLanguage(String studentId, CertificateType certificateType);

    @Query("SELECT u.id  FROM ObtainCert AS u WHERE u.student.studentId = ?1 AND u.certificateType = ?2")
    Integer findId(String studentId,CertificateType certificateType);

    @Query("SELECT u.levelLanguage FROM ObtainCert AS u WHERE u.student.studentId = ?1 ")
    List<LevelLanguage> findObtainCertByStudentId(String studentId);



//    @Modifying
//    @Query("DELETE  FROM ObtainCert AS u WHERE u.student.studentId = ?1 AND u.levelLanguage = ?2 ")
//    void deleteByStudentIdAndLevel(String studentId,LevelLanguage levelLanguage);
}
