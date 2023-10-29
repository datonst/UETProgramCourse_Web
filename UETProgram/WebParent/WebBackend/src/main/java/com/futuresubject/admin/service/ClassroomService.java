package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.mapper.ClassroomMapper;
import com.futuresubject.admin.mapper.FacultyMapper;
import com.futuresubject.admin.repository.ClassroomRepository;
import com.futuresubject.common.entity.Classroom;
import com.futuresubject.common.entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Classroom insert(ClassroomDto classroomDto) {
        Classroom classroom = ClassroomMapper.INSTANCE.toEntity(classroomDto);
        String classFullName = classroomDto.getCohort()
                + "-" + classroomDto.getNameClass();
        Integer id = classroomRepository.findId(classFullName);
        if(id!=null) {
            classroom.setId(id);
        }
        return classroomRepository.save(classroom);
    }

    public ClassroomDto get(String classFullName) throws NotFoundDataExeption {
        try {
            Classroom classroom = classroomRepository.findByCohortAndAndNameClass(classFullName);
            ClassroomDto classroomDto = ClassroomMapper.INSTANCE.toDto(classroom);
            return classroomDto;
        } catch (NoSuchElementException ex){
            throw new NotFoundDataExeption("Could not find any faculty with full name " + classFullName);
        }
    }


    public void deleteClassFullName(String classFullName) throws NotFoundDataExeption {
        classroomRepository.deleteByCohortAndAndNameClass(classFullName);
    }
    public boolean isExist(ClassroomDto classroomDto) {
        String classFullName = classroomDto.getNameClass() + "-" + classroomDto.getCohort();
        return classroomRepository.findId(classFullName) != null;
    }


}
