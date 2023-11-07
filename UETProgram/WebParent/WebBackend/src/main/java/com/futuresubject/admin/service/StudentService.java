package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.admin.mapper.StudentMapper;
import com.futuresubject.admin.repository.ClassroomRepository;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.common.entity.Classroom;
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
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<StudentDto> listAll() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> studentList = (List<Student>) studentRepository.findAll();
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
        Long countById=studentRepository.countByStudentId(studentid);// can use findById ==Null
        if(countById==null || countById==0){
            throw new NotFoundDataExeption("Could not find any user with ID " +studentid );
        }
        studentRepository.deleteById(studentid);
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
