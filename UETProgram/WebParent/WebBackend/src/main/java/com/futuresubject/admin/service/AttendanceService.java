package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.mapper.AttendanceMapper;
import com.futuresubject.admin.repository.AttendanceRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.common.entity.Attendance;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProgramRepository programRepository;

    public Attendance save(AttendanceDto attendanceDto) {
        Attendance attendance = AttendanceMapper.INSTANCE.toEntity(attendanceDto);
        String studentId = attendanceDto.getStudentId();
        if (studentId!=null) {
            if (!studentId.isEmpty()) {
                Optional<Student> findStudent = studentRepository.findById(studentId);
                findStudent.ifPresent(attendance::setStudent);
            }
        }
        String programFullCode = attendanceDto.getProgramFullCode();
        if (programFullCode!=null) {
            if (!programFullCode.isEmpty()) {
                Program findprogram = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
                attendance.setProgram(findprogram);
            }
        }
        attendanceRepository.save(attendance);
        return attendance;
    }
}
