package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.ProgramDto;
import com.futuresubject.admin.mapper.ProgramMapper;
import com.futuresubject.admin.repository.FacultyRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.Program;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    public List<ProgramDto> findAll() {

        return ProgramMapper.INSTANCE.toDtoList((List<Program>) programRepository.findAll());
    }

    public Program save(ProgramDto programDto) {
        Program program = ProgramMapper.INSTANCE.toEntity(programDto);
        String facultyName = programDto.getFacultyName();
        if (facultyName!=null) {
            if (!facultyName.isEmpty()) {
                System.out.println("print");
                Faculty faculty = facultyRepository.findByFacultyName(facultyName);
                program.setFaculty(faculty);
            }
        }
        return programRepository.save(program);
    }

    public Program findByProgramCodeAndAndPeriod (String programFullCode){
        return programRepository.findByProgramCodeAndAndPeriod(programFullCode);
    }
    public List<String> listOfProgramFullCode() {
        return programRepository.listOfProgramFullCode();
    }
}
