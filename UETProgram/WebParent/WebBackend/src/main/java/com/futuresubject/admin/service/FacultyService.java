package com.futuresubject.admin.service;


import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.mapper.FacultyMapper;
import com.futuresubject.admin.repository.FacultyRepository;
import com.futuresubject.common.entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public List<FacultyDto> findAll() {
        return FacultyMapper.INSTANCE.toDtoList((List<Faculty>) facultyRepository.findAll());
    }
    public List<String> listOfFacultyName() {
        return facultyRepository.listOfFacultyName();
    }
    public Faculty save(FacultyDto facultyDto) {
        Faculty faculty = FacultyMapper.INSTANCE.toEntity(facultyDto);
        return facultyRepository.save(faculty);
    }
}
