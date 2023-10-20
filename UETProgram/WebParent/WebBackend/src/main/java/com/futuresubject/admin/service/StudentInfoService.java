package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.SubjectInfoDto;
import com.futuresubject.admin.mapper.StudentInfoMapper;
import com.futuresubject.admin.mapper.SubjectInfoMapper;
import com.futuresubject.admin.repository.*;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class StudentInfoService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private MarkSubjectRepository markSubjectRepository;
    @Autowired
    Program_SubjectRepository programSubjectRepository;
    public List<SubjectInfoDto> getAllSubject(String mssv, String programFullCode) {
        List<SubjectInfoDto> subjectInfoDtoList = new ArrayList<>();
        List<Subject> SubjectList = programSubjectRepository.findAllSubject(programFullCode);
        for (Subject subject : SubjectList) {
            SubjectInfoDto subjectInfoDto = SubjectInfoMapper.INSTANCE.toDto(subject);
            subjectInfoDtoList.add(subjectInfoDto);
        }
        return subjectInfoDtoList;
    }
    public StudentInfoDto getStudent(String mssv) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findById(mssv).get();
            StudentInfoDto stDto= StudentInfoMapper.INSTANCE.toDto(student);
            List<Program> listP =attendanceRepository.listOfProgram(student);
            for (Program p : listP) {
                stDto.addProgramFullCode(p.getProgramCode() + '-' + p.getPeriod());
            }
            return stDto;
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Could not find any user with mssv " + mssv);
        }
    }
    public List<SubjectInfoDto> getFinishedSubject(String mssv, String programFullCode) {
        List<SubjectInfoDto> subjectInfoDtoList = new ArrayList<>();
        List<MarkSubject> markSubjectList = markSubjectRepository.findMarkList(mssv,programFullCode);
        for (MarkSubject markSubject : markSubjectList) {
            SubjectInfoDto subjectInfoDto = SubjectInfoMapper.INSTANCE.toDto(markSubject.getSubject());
            subjectInfoDto.setMark(markSubject.getMark());
            subjectInfoDtoList.add(subjectInfoDto);
        }
        return subjectInfoDtoList;
    }

    public List<SubjectInfoDto> getUnfinishedSubject(String mssv, String programFullCode) {
        List<SubjectInfoDto> subjectInfoDtoList = new ArrayList<>();
        List<Subject> SubjectList = programSubjectRepository.findSubjectUnfinished(mssv,programFullCode);
        for (Subject subject : SubjectList) {
            SubjectInfoDto subjectInfoDto = SubjectInfoMapper.INSTANCE.toDto(subject);
            subjectInfoDtoList.add(subjectInfoDto);
        }
        return subjectInfoDtoList;
    }
}
