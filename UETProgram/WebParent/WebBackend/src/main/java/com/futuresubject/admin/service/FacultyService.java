package com.futuresubject.admin.service;


import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.mapper.FacultyMapper;
import com.futuresubject.admin.repository.FacultyRepository;
import com.futuresubject.common.entity.Entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyDto get(String facultyName) throws NotFoundDataExeption {
        try {
            Faculty faculty = facultyRepository.findByFacultyName(facultyName);
            FacultyDto facultyDto = FacultyMapper.INSTANCE.toDto(faculty);
            return facultyDto;
        } catch (NoSuchElementException ex){
            throw new NotFoundDataExeption("Could not find any faculty with full name " + facultyName);
        }
    }


    public void deleteByFacultyName(String facultyName) throws NotFoundDataExeption {
        facultyRepository.deleteByFacultyName(facultyName);
    }

    public List<FacultyDto> findAll() {
        return FacultyMapper.INSTANCE.toDtoList((List<Faculty>) facultyRepository.findAll());
    }
    public List<String> listOfFacultyName() {
        return facultyRepository.listOfFacultyName();
    }
    public Faculty insert(FacultyDto facultyDto) {
        Faculty faculty = FacultyMapper.INSTANCE.toEntity(facultyDto);
        Integer id = facultyRepository.findId(facultyDto.getFacultyName());
        if (id!=null) {
            faculty.setId(id);
        }
        return facultyRepository.save(faculty);
    }

    public boolean isExist(FacultyDto facultyDto) {
        return facultyRepository.findId(facultyDto.getFacultyName()) != null;
    }
}
