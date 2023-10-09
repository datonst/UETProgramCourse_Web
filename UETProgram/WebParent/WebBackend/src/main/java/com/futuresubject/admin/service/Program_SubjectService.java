package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.Program_SubjectDto;
import com.futuresubject.admin.mapper.Program_SubjectMapper;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.Program_SubjectRepository;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class Program_SubjectService {
    @Autowired
    Program_SubjectRepository programSubjectRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    SubjectRepository subjectRepository;
    public Program_Subject save(Program_SubjectDto programSubjectDto) {
        Program_Subject programSubject = Program_SubjectMapper
                .INSTANCE.toEntity(programSubjectDto);
        String programFullCode = programSubjectDto.getProgramFullCode();
        if (programFullCode!=null) {
            if (!programFullCode.isEmpty()) {
                Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
                programSubject.setProgram(program);
            }
        }
        String subjectId = programSubjectDto.getSubjectId();
        if (subjectId!=null) {
            if (!subjectId.isEmpty()) {
                Optional<Subject> findSubject = subjectRepository.findById(subjectId);
                findSubject.ifPresent(programSubject::setSubject);
            }
        }
         return programSubjectRepository.save(programSubject);
    }
}
