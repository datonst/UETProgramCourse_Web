package com.futuresubject.admin.student;

import com.futuresubject.common.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
}
