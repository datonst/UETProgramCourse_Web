package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.SubjectInfoDto;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.ClassroomService;
import com.futuresubject.admin.service.StudentInfoService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchStudentController {
    @Autowired
    StudentInfoService studentInfoService;


    @GetMapping("/search")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public String listStudents(Model model) {
        return "";
    }

    @GetMapping("/searchid/{mssv}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public StudentInfoDto searchStudentId(@PathVariable(name="mssv") String mssv) throws StudentNotFoundException {
        return studentInfoService.getStudent(mssv);
    }

    @GetMapping("/searchid/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectInfoDto> searchFinishedSubject(@PathVariable(name="mssv") String mssv,
                                                      @PathVariable(name="programFullCode") String programFullCode,
                                                      @RequestParam(value = "status",required = false) String status) throws StudentNotFoundException {
        if ("finished".equals(status)) {
            return studentInfoService.getFinishedSubject(mssv, programFullCode);
        } else if ("unfinished".equals(status)) {
            return studentInfoService.getUnfinishedSubject(mssv, programFullCode);
        } else {
            return studentInfoService.getAllSubject(mssv, programFullCode);
        }
    }
}
