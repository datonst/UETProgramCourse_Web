package com.futuresubject.admin.repository;

import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.Program;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
    @Query("SELECT u.facultyName FROM Faculty AS u")
    public List<String> listOfFacultyName();

    @Query("SELECT u  FROM Faculty AS u WHERE u.facultyName = ?1 ")
    Faculty findByFacultyName(String facultyName);

}
