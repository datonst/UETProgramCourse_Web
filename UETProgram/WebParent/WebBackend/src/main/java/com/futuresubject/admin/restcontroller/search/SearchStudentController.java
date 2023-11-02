package com.futuresubject.admin.restcontroller.search;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.search.AverageMark;
import com.futuresubject.admin.dto.search.DownGrade;
import com.futuresubject.admin.dto.search.EnoughCertificate;
import com.futuresubject.admin.dto.search.SubjectInfoDto;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.admin.service.search.StudentInfoService;
import com.futuresubject.common.entity.Enum.RoleType;
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

    @GetMapping("/searchSubject/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectInfoDto> searchFinishedSubject(@PathVariable(name="mssv") String mssv,
                                                      @PathVariable(name="programFullCode") String programFullCode,
                                                      @RequestParam(value = "status",required = false) String status,
                                                      @RequestParam(value = "roleType",required = false) RoleType roleType) {
        if ("finished".equals(status)) {
            List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubject(mssv, programFullCode, roleType);
            return dtos;
        } else if ("unfinished".equals(status)) {
            return studentInfoService.getUnfinishedSubject(mssv, programFullCode,roleType);
        } else {
            return studentInfoService.getAllSubject(mssv, programFullCode);
        }
    }


//    //Phần này để TEST
//    @GetMapping("/sea")
//    public void se() {
//        System.out.println(markSubjectService.sumMark("22028245","cn8-2019"));
//    }
//    @GetMapping("/seaw/s")
//    public void ses() {
//        markSubjectService.sumMarkOfStudentList();
//    }

    @GetMapping("/getAverageMark/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public AverageMark getAverageMarkByRoleType(@PathVariable(name="mssv") String mssv,
                                                @PathVariable(name="programFullCode") String programFullCode,
                                                @RequestParam(value = "roleType",required = false) RoleType roleType) {
            AverageMark averageMark = new AverageMark();
            List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubject(mssv, programFullCode, roleType);
            averageMark.setAverageMark(studentInfoService.getMaxAverageMark(dtos,programFullCode,roleType));
            return averageMark;
    }

    @GetMapping("/downgraded/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public DownGrade getDownGraded(@PathVariable(name="mssv") String mssv,
                                @PathVariable(name="programFullCode") String programFullCode) {
        DownGrade downGrade = new DownGrade();
        downGrade.setDownGrade(studentInfoService.downGraded(mssv, programFullCode));
        return downGrade;
    }

    @GetMapping("/enoughCert/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public EnoughCertificate enoughCert(@PathVariable(name="mssv") String mssv,
                                        @PathVariable(name="programFullCode") String programFullCode) {
        EnoughCertificate enoughCertificate = new EnoughCertificate();
        enoughCertificate.setEnoughCert(studentInfoService.enoughCertificate(mssv, programFullCode));
        return enoughCertificate;
    }
}
