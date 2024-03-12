package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    @Query("SELECT concat(u.cohort,'-',u.nameClass)  FROM Classroom AS u")
    List<String> listOfClassroom();

    @Query("SELECT u  FROM Classroom AS u WHERE concat(u.cohort,'-',u.nameClass) = ?1 ")
    Classroom findByCohortAndAndNameClass(String classFullName);

    @Modifying
    @Query("DELETE FROM Classroom AS u WHERE concat(u.cohort,'-',u.nameClass) = ?1 ")
    void deleteByCohortAndAndNameClass(String classFullName);

    @Query("SELECT u.id  FROM Classroom AS u WHERE concat(u.cohort,'-',u.nameClass) = ?1 ")
    Integer findId(String classFullName);


    @Query("SELECT DISTINCT u.cohort  FROM Classroom AS u")
    List<String> findCohort ();
}
