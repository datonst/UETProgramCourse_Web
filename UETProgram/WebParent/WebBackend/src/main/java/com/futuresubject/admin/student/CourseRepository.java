package com.futuresubject.admin.student;

import com.futuresubject.common.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
}
