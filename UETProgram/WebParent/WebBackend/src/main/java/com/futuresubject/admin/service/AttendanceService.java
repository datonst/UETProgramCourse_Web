package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.mapper.AttendanceMapper;
import com.futuresubject.admin.mapper.ClassroomMapper;
import com.futuresubject.admin.repository.AttendanceRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.common.entity.Attendance;
import com.futuresubject.common.entity.Classroom;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    public List<AttendanceDto> findAllAttendance(Pageable pagination) {
        Page<Attendance> attendanceList = attendanceRepository.findAll(pagination);
        List<AttendanceDto> attendanceDtoList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            AttendanceDto attendanceDto =
                    AttendanceMapper.INSTANCE.toDto(attendance);
            attendanceDtoList.add(attendanceDto);
        }
        return attendanceDtoList;
    }

    public Attendance insert(AttendanceDto attendanceDto) {
        Attendance attendance = AttendanceMapper.INSTANCE.toEntity(attendanceDto);
        String studentId = attendanceDto.getStudentId();
        String programFullCode = attendanceDto.getProgramFullCode();
        Integer id = attendanceRepository.findId(studentId,programFullCode);
        if (id!=null) {
            attendance.setId(id);
        }
        attendance.setStudent(studentRepository.findById(studentId).get());
        attendance.setProgram(programRepository.findByProgramCodeAndAndPeriod(programFullCode));
        return attendanceRepository.save(attendance);

    }

    public boolean isExist(AttendanceDto attendanceDto) {
        return attendanceRepository.findId(attendanceDto.getStudentId()
                ,attendanceDto.getProgramFullCode()) != null;
    }


    public AttendanceDto get(String subjectId, String programFullCode) throws NotFoundDataExeption {
        try {
            Attendance attendance = attendanceRepository
                    .findAttendanceByStudentIdAndProgramId(subjectId,programFullCode);
            AttendanceDto attendanceDto = AttendanceMapper.INSTANCE.toDto(attendance);
            return attendanceDto;
        } catch (NoSuchElementException ex){
            throw new NotFoundDataExeption("Could not find any attendances with subjectId "
                    + subjectId
                    +" programfullCode: " +programFullCode);
        }
    }

    public void updateFromDto(AttendanceDto attendanceDto) {
        Attendance attendance = AttendanceMapper.INSTANCE.toEntity(attendanceDto);
        String studentId = attendanceDto.getStudentId();
        String programFullCode = attendanceDto.getProgramFullCode();
        Integer id = attendanceRepository.findId(studentId,programFullCode);
        if (id!=null) {
            attendance.setId(id);
        }
        attendance.setStudent(studentRepository.findById(studentId).get());
        attendance.setProgram(programRepository.findByProgramCodeAndAndPeriod(programFullCode));
        attendanceRepository.save(attendance);
    }


    public void deleteAttendance(String subjectId, String programFullCode) {
        Integer id = attendanceRepository.findId(subjectId,programFullCode);
        attendanceRepository.deleteById(id);
//        attendanceRepository.deleteAttendanceByStudentIdAndProgramId(subjectId,programFullCode);
    }
}
