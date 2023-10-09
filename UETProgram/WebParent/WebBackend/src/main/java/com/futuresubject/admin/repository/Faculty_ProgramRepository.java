package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Faculty_Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Faculty_ProgramRepository extends CrudRepository<Faculty_Program, Integer> {
}
