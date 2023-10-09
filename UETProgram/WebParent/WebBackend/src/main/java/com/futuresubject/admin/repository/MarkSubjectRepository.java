package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.MarkSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkSubjectRepository extends CrudRepository<MarkSubject, Integer> {
}
