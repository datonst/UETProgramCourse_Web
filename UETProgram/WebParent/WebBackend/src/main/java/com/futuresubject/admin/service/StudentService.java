package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.admin.mapper.StudentMapper;
import com.futuresubject.admin.repository.ClassroomRepository;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.common.entity.Entity.Classroom;
import com.futuresubject.common.entity.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<StudentDto> listAll(Pageable pagination) {
        List<StudentDto> studentDtos = new ArrayList<>();
        Page<Student> studentList = studentRepository.findAll(pagination);
        for (Student s : studentList) {
            studentDtos.add(StudentMapper.INSTANCE.toDto(s));
        }
        return studentDtos;
    }

    public List<String> listOfStudentId() {
        return studentRepository.listOfStudentId();
    }
    public StudentDto get(String mssv) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findById(mssv).get();
            return StudentMapper.INSTANCE.toDto(student);
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Could not find any user with mssv " + mssv);
        }
    }

    public Student insert(StudentDto studentDto) {
        Student student = StudentMapper.INSTANCE.toEntity(studentDto);
        String classFullName = studentDto.getClassFullName();
        Classroom classroom = classroomRepository.findByCohortAndAndNameClass(classFullName);
        student.setClassroom(classroom);
        return studentRepository.save(student);
    }

    public void deleteByStudentid(String studentid) throws NotFoundDataExeption {
        Student student= studentRepository.findById(studentid).get();
        studentRepository.delete(student);
    }

    public void updateFromDto(StudentDto studentDto) {
        Student student = StudentMapper.INSTANCE.toEntity(studentDto);
        String classFullName = studentDto.getClassFullName();
        Classroom classroom = classroomRepository.findByCohortAndAndNameClass(classFullName);
        student.setClassroom(classroom);
        studentRepository.save(student);
    }
    public boolean isExist(StudentDto studentDto) {
        return studentRepository.existsById(studentDto.getStudentId());
    }
}
