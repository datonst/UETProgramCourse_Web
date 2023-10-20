package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Attendance;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository  extends CrudRepository<Attendance, Integer> {
    @Query("SELECT u.program  FROM Attendance AS u WHERE u.student = ?1")
    List<Program> listOfProgram(Student student);
}
