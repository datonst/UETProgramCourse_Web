package com.futuresubject.admin.service;

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
        return StudentMapper.INSTANCE.toDtoList((List<Student>)studentRepository.findAll());
    }

    public List<String> listOfStudentId() {
        return studentRepository.listOfStudentId();
    }
    public Student get(String mssv) throws StudentNotFoundException {
        try {
            return studentRepository.findById("220").get();
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Could not find any user with mssv " + mssv);
        }
    }

    public Student save(StudentDto studentDto) {
        Student student = StudentMapper.INSTANCE.toEntity(studentDto);
        String classFullName = studentDto.getClassFullName();
        if (classFullName!=null) {
            if (!classFullName.isEmpty()) {
                Classroom classroom = classroomRepository.findByCohortAndAndNameClass(classFullName);
                student.setClassroom(classroom);
            }
        }
        return studentRepository.save(student);
    }
}
