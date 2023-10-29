package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.ProgramDto;
import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.mapper.MarkSubjectMapper;
import com.futuresubject.admin.mapper.ProgramMapper;
import com.futuresubject.admin.repository.MarkSubjectRepository;
import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MarkSubjectService {
    @Autowired
    private MarkSubjectRepository markSubjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public MarkSubject insert(MarkSubjectDto markSubjectDto) throws NotFoundDataExeption {
        String studentId = markSubjectDto.getStudentId();
        String subjectId = markSubjectDto.getSubjectId();
        if (studentId==null || subjectId ==null) {
            throw new NotFoundDataExeption("subjectId and studentId  is null");
        }
        MarkSubject markSubject = MarkSubjectMapper.INSTANCE.toEntity(markSubjectDto);
        markSubject.setStudent(studentRepository.findById(studentId).get());
        markSubject.setSubject(subjectRepository.findById(subjectId).get());
        Integer id = markSubjectRepository.findId(studentId,subjectId);
        if (id!=null) {
            markSubject.setId(id);
        }
        return markSubjectRepository.save(markSubject);
    }

    public boolean isExist(MarkSubjectDto markSubjectDto) {
        String studentId = markSubjectDto.getStudentId();
        String subjectId = markSubjectDto.getSubjectId();
        if (studentId == null || subjectId==null) {
            return false;
        }
        Integer id = markSubjectRepository.findId(studentId,subjectId);
        return id != null;
    }

    public MarkSubjectDto get(String studentId, String subjectId) throws NotFoundDataExeption {
        if (studentId==null || subjectId ==null) {
            throw new NotFoundDataExeption("subjectId and studentId  is null");
        }
        try {
            MarkSubject markSubject = markSubjectRepository.findMarkSubject(studentId,subjectId);
            MarkSubjectDto markSubjectDto = MarkSubjectMapper.INSTANCE.toDto(markSubject);
            return markSubjectDto;
        } catch (NoSuchElementException ex){
            throw new NotFoundDataExeption("Could not find any mark with subjectId and studentId "
                    + studentId + " " + "subjectId" );
        }
    }
    public void deleteMark(String studentId, String subjectId) {
        if (studentId==null || subjectId==null) {
            return;
        }
        markSubjectRepository.deleteMark(studentId,subjectId);
    }

    public void updateMark(String studentId, String subjectId, Double mark) throws NotFoundDataExeption {
        if (studentId==null || subjectId ==null || mark==null) {
            throw new NotFoundDataExeption("subjectId or studentId  or mark  is null");
        }
        markSubjectRepository.updateMark(studentId,subjectId,mark);
    }

}
