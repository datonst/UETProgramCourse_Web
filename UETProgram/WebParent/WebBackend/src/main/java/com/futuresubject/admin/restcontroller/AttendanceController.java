package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.service.AttendanceService;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.common.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private ProgramService programService;


    @GetMapping("/attendances/new")
    public AttendanceDto getAttendanceDtoVal(){
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setListOfStudentId(studentService.listOfStudentId());
        attendanceDto.setListOfProgramFullCode(programService.listOfProgramFullCode());
        return attendanceDto;
    }

    @PostMapping("/attendances/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Attendance saveAttendace(@RequestBody AttendanceDto attendanceDto) {
        return attendanceService.save(attendanceDto);
    }
}
