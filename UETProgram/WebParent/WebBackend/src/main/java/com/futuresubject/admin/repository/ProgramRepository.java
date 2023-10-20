package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Program;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Integer> {
    @Query("SELECT concat(u.programCode,'-',u.period)  FROM Program AS u")
    List<String> listOfProgramFullCode();

    @Query("SELECT u  FROM Program AS u WHERE concat(u.programCode,'-',u.period) = ?1 ")
    Program findByProgramCodeAndAndPeriod(String ProgramFullCode);


}
