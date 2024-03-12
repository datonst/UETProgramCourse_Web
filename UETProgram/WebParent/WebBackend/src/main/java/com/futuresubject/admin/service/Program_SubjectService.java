package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.Program_SubjectDto;
import com.futuresubject.admin.mapper.Program_SubjectMapper;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.Program_SubjectRepository;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.Entity.Program;
import com.futuresubject.common.entity.JoinTable.Program_Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Program_SubjectService {
    @Autowired
    Program_SubjectRepository programSubjectRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    SubjectRepository subjectRepository;
    public Program_Subject insert(Program_SubjectDto programSubjectDto) {
        Program_Subject programSubject = Program_SubjectMapper
                .INSTANCE.toEntity(programSubjectDto);
        String programFullCode = programSubjectDto.getProgramFullCode();
        Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
        programSubject.setProgram(program);
        String subjectId = programSubjectDto.getSubjectId();
        programSubject.setSubject(subjectRepository.findById(subjectId).get());
        Integer id = programSubjectRepository.findId(subjectId,programFullCode);
        if (id!=null) {
            programSubject.setId(id);
        }
         return programSubjectRepository.save(programSubject);
    }
    public boolean isExist(Program_SubjectDto programSubjectDto) {
        return programSubjectRepository.findId(programSubjectDto.getSubjectId()
                ,programSubjectDto.getProgramFullCode()) != null;
    }
}
