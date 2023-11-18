package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Faculty_Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Faculty_ProgramRepository extends JpaRepository<Faculty_Program, Integer> {
    @Query("SELECT u.id FROM Faculty_Program AS u " +
            " WHERE u.faculty.facultyName = ?1 AND concat(u.program.programCode,'-',u.program.period) = ?2")
    Integer findId(String facultyName, String programFullCode);
}
