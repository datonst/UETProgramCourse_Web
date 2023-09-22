package com.futuresubject.admin.student;

import com.futuresubject.common.entity.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, String> {
}
