package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Classroom;
import com.futuresubject.common.entity.Program;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {
    @Query("SELECT concat(u.cohort,'-',u.nameClass)  FROM Classroom AS u")
    List<String> listOfClassroom();

    @Query("SELECT u  FROM Classroom AS u WHERE concat(u.cohort,'-',u.nameClass) = ?1 ")
    Classroom findByCohortAndAndNameClass(String classFullName);

}
