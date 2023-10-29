package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.service.AttendanceService;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.common.entity.Attendance;
import com.futuresubject.common.entity.MarkSubject;
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
    public Attendance saveAttendace(@RequestBody AttendanceDto attendanceDto) throws NotFoundDataExeption {
//        if (attendanceDto.getStudentId() == null
//                || attendanceDto.getProgramFullCode()==null) {
//            throw new NotFoundDataExeption("Not found - attendance is null");
//        }
        return attendanceService.insert(attendanceDto);
    }

    @GetMapping("/attendances/edit/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public AttendanceDto getEditAttendance(@PathVariable(name = "element") String element) throws NotFoundDataExeption {
        String[] arrOfStr = element.split("&", 2);
        String subjectId = arrOfStr[0];
        String programFullCode = arrOfStr[1];
        return attendanceService.get(subjectId,programFullCode);
    }
    @PutMapping("/attendances/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putEditAttendance(@RequestBody AttendanceDto attendanceDto) {
        attendanceService.updateFromDto(attendanceDto);
    }

    @DeleteMapping("/attendances/delete/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditAttendance(@PathVariable(name = "element") String element) throws NotFoundDataExeption {
        String[] arrOfStr = element.split("&", 2);
        String subjectId = arrOfStr[0];
        String programFullCode = arrOfStr[1];
        attendanceService.deleteAttendance(subjectId,programFullCode);
    }
}
