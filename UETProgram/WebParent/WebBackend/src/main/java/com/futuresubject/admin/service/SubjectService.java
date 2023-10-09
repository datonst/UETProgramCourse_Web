package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.mapper.SubjectMapper;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDto> findAll() {
        return SubjectMapper.INSTANCE.toDtoList((List<Subject>) subjectRepository.findAll());
    }

    public List<String> listOfSubjectId() {
        return subjectRepository.listOfSubjectId();
    }
    public Subject save(SubjectDto subjectDto) {
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);
        for (String s : subjectDto.getPrerequisiteSubjectId()) {
            Optional<Subject> pre_student = subjectRepository.findById(s);
            pre_student.ifPresent(subject::addPrerequisite);
//            if (pre_student.isPresent()) {
//                subject.addPrerequisite(pre_student.get());
//            }
        }
        return subjectRepository.save(subject);
    }

}
