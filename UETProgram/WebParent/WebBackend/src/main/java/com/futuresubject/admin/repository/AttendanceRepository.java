package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository  extends CrudRepository<Attendance, Integer> {
}
