package com.futuresubject.admin.restcontroller.search;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.SubjectInfoDto;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.admin.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchStudentController {
    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    MarkSubjectService markSubjectService;


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
                                                      @RequestParam(value = "status",required = false) String status) {
        if ("finished".equals(status)) {
            return studentInfoService.getFinishedSubject(mssv, programFullCode);
        } else if ("unfinished".equals(status)) {
            return studentInfoService.getUnfinishedSubject(mssv, programFullCode);
        } else {
            return studentInfoService.getAllSubject(mssv, programFullCode);
        }
    }


    //Phần này để TEST
    @GetMapping("/sea")
    public void se() {
        System.out.println(markSubjectService.sumMark("22028245","cn8-2019"));
    }
    @GetMapping("/seaw/s")
    public void ses() {
        markSubjectService.sumMarkOfStudentList();
    }
}
