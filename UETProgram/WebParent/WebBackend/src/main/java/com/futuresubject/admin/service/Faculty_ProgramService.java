package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.Faculty_ProgramDto;
import com.futuresubject.admin.mapper.Faculty_ProgramMapper;
import com.futuresubject.admin.repository.FacultyRepository;
import com.futuresubject.admin.repository.Faculty_ProgramRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.common.entity.Entity.Faculty;
import com.futuresubject.common.entity.JoinTable.Faculty_Program;
import com.futuresubject.common.entity.Entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Faculty_ProgramService {
    @Autowired
    private Faculty_ProgramRepository facultyProgramRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    FacultyRepository facultyRepository;

    public Faculty_Program insert(Faculty_ProgramDto facultyProgramDto) {
        Faculty_Program facultyProgram = Faculty_ProgramMapper.INSTANCE.toEntity(facultyProgramDto);
        String facultyName = facultyProgramDto.getFacultyName();
        String programFullCode = facultyProgramDto.getProgramFullCode();
        Faculty faculty = facultyRepository.findByFacultyName(facultyName);
        facultyProgram.setFaculty(faculty);
        Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
        facultyProgram.setProgram(program);
        Integer id = facultyProgramRepository.findId(facultyName,programFullCode);
        if (id!=null) {
            facultyProgram.setId(id);
        }
        return facultyProgramRepository.save(facultyProgram);
    }
    public boolean isExist(Faculty_ProgramDto facultyProgramDto) {
        return facultyProgramRepository.findId(facultyProgramDto.getFacultyName()
                ,facultyProgramDto.getProgramFullCode()) != null;
    }
}
