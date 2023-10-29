package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    @Query("SELECT u.studentId FROM Student AS u")
    public List<String> listOfStudentId();

    Long countByStudentId(String studentid);
}
