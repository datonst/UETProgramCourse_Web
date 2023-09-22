package com.futuresubject.admin.student;

import com.futuresubject.common.entity.Student;
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

    public List<Student> listAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student get(String mssv) throws StudentNotFoundException {
        try {
            return studentRepository.findById(mssv).get();
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Could not find any user with mssv " + mssv);
        }
    }
}
