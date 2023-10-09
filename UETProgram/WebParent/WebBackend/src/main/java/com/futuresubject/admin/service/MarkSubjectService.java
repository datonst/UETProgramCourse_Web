package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.mapper.MarkSubjectMapper;
import com.futuresubject.admin.repository.MarkSubjectRepository;
import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MarkSubject save(MarkSubjectDto markSubjectDto) {
        MarkSubject markSubject = MarkSubjectMapper.INSTANCE.toEntity(markSubjectDto);
        String studentId = markSubjectDto.getStudentId();
        if (studentId!=null) {
            if (!studentId.isEmpty()) {
                Optional<Student> findStudent = studentRepository.findById(studentId);
                findStudent.ifPresent(markSubject::setStudent);
            }
        }
        String subjectId = markSubjectDto.getSubjectId();
        if (subjectId!=null) {
            if (!subjectId.isEmpty()) {
                Optional<Subject> findSubject = subjectRepository.findById(subjectId);
                findSubject.ifPresent(markSubject::setSubject);
            }
        }
        return markSubjectRepository.save(markSubject);
    }
}
