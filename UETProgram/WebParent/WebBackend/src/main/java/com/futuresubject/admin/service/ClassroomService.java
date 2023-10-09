package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.admin.mapper.ClassroomMapper;
import com.futuresubject.admin.repository.ClassroomRepository;
import com.futuresubject.common.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<ClassroomDto> findAll() {
        return ClassroomMapper.INSTANCE.toDtoList((List<Classroom>) classroomRepository.findAll());
    }
    public List<String> listOfClassroom() {
        return classroomRepository.listOfClassroom();
    }
    public Classroom save(ClassroomDto classroomDto) {
        Classroom classroom = ClassroomMapper.INSTANCE.toEntity(classroomDto);
        return classroomRepository.save(classroom);
    }

}
