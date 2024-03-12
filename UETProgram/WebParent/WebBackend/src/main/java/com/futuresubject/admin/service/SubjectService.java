package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.mapper.SubjectMapper;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.Entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDto> findAll(Pageable pagination) {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        Page<Subject> subjects = subjectRepository.findAll(pagination);

        for (Subject subject : subjects) {
            subjectDtos.add(SubjectMapper.INSTANCE.toDto(subject));
        }
        System.out.println("âhhahahahahaha");
        return subjectDtos;
    }

    public SubjectDto get(String subjectid) throws NotFoundDataExeption {
        try {
            Subject subject =subjectRepository.findById(subjectid).get();
            SubjectDto subjectDto = SubjectMapper.INSTANCE.toDto(subject);
            for (String t : subjectDto.getPrerequisiteSubjectId()) {
                System.out.println(t);
            }
//            if (subject.getPrerequisiteSubject()!=null) {
//                List<String> list = new ArrayList<>();
//                System.out.println(subject.getSubjectName() + " : " + subject.getPrerequisiteSubject().size());
//                for (Subject s : subject.getPrerequisiteSubject()) {
//                    String subjectSubjectid = subject.getSubjectid();
//                    list.add(subjectSubjectid);
//                }
//                subjectDto.setPrerequisiteSubjectId(list);
//            }
            return subjectDto;
        } catch (NoSuchElementException ex){
            throw new NotFoundDataExeption("Could not find any subject with ID " + subjectid);
        }
    }
    public List<String> listOfSubjectId() {
        return subjectRepository.listOfSubjectId();
    }
    public Subject insert(SubjectDto subjectDto) {
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);
        for (String s : subjectDto.getPrerequisiteSubjectId()) {
            Optional<Subject> pre_student = subjectRepository.findById(s);
            pre_student.ifPresent(subject::addPrerequisite);

        }
        return subjectRepository.save(subject);
    }
    @Modifying
    public void deleteBySubjectid(String subjectid) {
        Subject subject = subjectRepository.findById(subjectid).get();
        subjectRepository.delete(subject);
    }

    public boolean isExist(SubjectDto subjectDto) {
        return subjectRepository.existsById(subjectDto.getSubjectid());
    }

    public void updateFromDto(SubjectDto subjectDto) {
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);
        for (String s : subjectDto.getPrerequisiteSubjectId()) {
            Optional<Subject> pre_student = subjectRepository.findById(s);
            pre_student.ifPresent(subject::addPrerequisite);

        }
        subjectRepository.save(subject);
    }
}
