package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Program_Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Program_SubjectRepository extends CrudRepository<Program_Subject, Integer> {
}
