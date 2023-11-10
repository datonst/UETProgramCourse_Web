package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.*;
import com.futuresubject.admin.mapper.ProgramMapper;
import com.futuresubject.admin.mapper.StudentMapper;
import com.futuresubject.admin.mapper.SubjectMapper;
import com.futuresubject.admin.repository.FacultyRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    public ProgramDto get(String programFullCode) throws NotFoundDataExeption {
        try {
            Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
            ProgramDto programDto = ProgramMapper.INSTANCE.toDto(program);
            return programDto;
        } catch (NoSuchElementException ex){
            throw new NotFoundDataExeption("Could not find any program with full code " + programFullCode);
        }
    }

    public void deleteByProgramFullCode(String programFullCode) {
        Integer id = programRepository.findId(programFullCode);
        if (id!=null && id != 0) {
            programRepository.deleteById(id);
        }
    }

    public Program insert(ProgramDto programDto) {
        Program program = ProgramMapper.INSTANCE.toEntity(programDto);
        String facultyName = programDto.getFacultyName();
        String programFullCode = programDto.getProgramCode()
                + "-" +programDto.getPeriod();
        Integer id = programRepository.findId(programFullCode);
        if(id!=null){
            program.setId(id);
        }
        Faculty faculty = facultyRepository.findByFacultyName(facultyName);
        program.setFaculty(faculty);
        return programRepository.save(program);
    }

    public Program findByProgramCodeAndAndPeriod (String programFullCode){
        return programRepository.findByProgramCodeAndAndPeriod(programFullCode);
    }
    public List<String> listOfProgramFullCode() {
        return programRepository.listOfProgramFullCode();
    }
    public boolean isExist(ProgramDto programDto) {
        String programFullCode = programDto.getProgramCode() + "-"
                + programDto.getPeriod();
        return programRepository.findId(programFullCode) != null;
    }

    public Program updateFromDto(ProgramDto programDto) {
        Program program = ProgramMapper.INSTANCE.toEntity(programDto);
        String facultyName = programDto.getFacultyName();
        String programFullCode = programDto.getProgramCode()
                + "-" +programDto.getPeriod();
        Integer id = programRepository.findId(programFullCode);
        if(id!=null){
            program.setId(id);
        }
        Faculty faculty = facultyRepository.findByFacultyName(facultyName);
        program.setFaculty(faculty);
        return programRepository.save(program);
    }
}
