package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.Faculty_ProgramDto;
import com.futuresubject.admin.mapper.Faculty_ProgramMapper;
import com.futuresubject.admin.repository.FacultyRepository;
import com.futuresubject.admin.repository.Faculty_ProgramRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.Faculty_Program;
import com.futuresubject.common.entity.Program;
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

    public Faculty_Program save(Faculty_ProgramDto facultyProgramDto) {
        Faculty_Program facultyProgram = Faculty_ProgramMapper.INSTANCE.toEntity(facultyProgramDto);
        String facultyName = facultyProgramDto.getFacultyName();
        if (facultyName!=null) {
            if (!facultyName.isEmpty()) {
                Faculty faculty = facultyRepository.findByFacultyName(facultyName);
                facultyProgram.setFaculty(faculty);
            }
        }
        String programFullCode = facultyProgramDto.getProgramFullCode();
        if (programFullCode!=null) {
            if (!programFullCode.isEmpty()) {
                Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
                facultyProgram.setProgram(program);
            }
        }
        return facultyProgramRepository.save(facultyProgram);
    }

}
