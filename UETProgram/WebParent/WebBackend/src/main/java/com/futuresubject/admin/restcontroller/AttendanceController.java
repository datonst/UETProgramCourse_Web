package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.service.AttendanceService;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.common.entity.JoinTable.Attendance;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private ProgramService programService;


    @GetMapping("/attendances/new")
    @RolesAllowed({"ROLE_ADMIN"})
    public AttendanceDto getAttendanceDtoVal(){
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setListOfStudentId(studentService.listOfStudentId());
        attendanceDto.setListOfProgramFullCode(programService.listOfProgramFullCode());
        return attendanceDto;
    }

    @PostMapping("/attendances/new")
    @RolesAllowed({"ROLE_ADMIN"})
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Attendance createAttendance(@RequestBody
                                           AttendanceDto attendanceDto) throws NotFoundDataExeption {

        if (attendanceDto.getStudentId() == null
                || attendanceDto.getProgramFullCode()==null) {
            throw new NotFoundDataExeption("Not found - attendance is null");
        }
        System.out.println("asdfasfsaf");
        return attendanceService.insert(attendanceDto);
    }
    @GetMapping("/attendances")
    @RolesAllowed({"ROLE_ADMIN"})
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<AttendanceDto> attendacesList(
            @RequestParam(value = "page", defaultValue = "0")  int page
            , @RequestParam(value = "size", defaultValue = "20") int size
    ) throws NotFoundDataExeption {
        Pageable pagination = PageRequest.of(page, size);
        return attendanceService.findAllAttendance(pagination);
    }
    @GetMapping("/attendances/edit/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public AttendanceDto getEditAttendance(@PathVariable(name = "element") String element) throws NotFoundDataExeption {
        String[] arrOfStr = element.split("&", 2);
        String subjectId = arrOfStr[0];
        String programFullCode = arrOfStr[1];
        AttendanceDto attendanceDto = attendanceService.get(subjectId,programFullCode);
        attendanceDto.setListOfProgramFullCode(programService.listOfProgramFullCode());
        attendanceDto.setListOfStudentId(studentService.listOfStudentId());
        return attendanceDto;
    }
    @PutMapping("/attendances/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"ROLE_ADMIN"})
    public void putEditAttendance(@RequestBody AttendanceDto attendanceDto) {
        attendanceService.updateFromDto(attendanceDto);
    }

    @DeleteMapping("/attendances/delete/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"ROLE_ADMIN"})
    public void deleteEditAttendance(@PathVariable(name = "element") String element) throws NotFoundDataExeption {
        String[] arrOfStr = element.split("&", 2);
        String subjectId = arrOfStr[0];
        String programFullCode = arrOfStr[1];
        attendanceService.deleteAttendance(subjectId,programFullCode);
    }
}
