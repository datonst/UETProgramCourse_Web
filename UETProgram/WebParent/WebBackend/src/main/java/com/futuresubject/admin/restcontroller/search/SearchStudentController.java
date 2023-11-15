package com.futuresubject.admin.restcontroller.search;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.search.*;
import com.futuresubject.admin.exporter.ExcelExporter;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.admin.service.search.StudentInfoService;
import com.futuresubject.common.entity.Enum.ProgramType;
import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Program;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SearchStudentController {
    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    MarkSubjectService markSubjectService;


    @GetMapping("/search")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public String listStudents() {
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
        List<SubjectInfoDto> dtos = null;
        if ("finished".equals(status)) {
             dtos= studentInfoService.getFinishedSubject(mssv, programFullCode, roleType);
        } else if ("unfinished".equals(status)) {
            dtos= studentInfoService.getUnfinishedSubject(mssv, programFullCode,roleType);
        } else {
            dtos = studentInfoService.getAllSubject(mssv, programFullCode);
        }
        return dtos;
    }

//    @GetMapping("export/excel/searchSubject/{mssv}/{programFullCode}")
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.OK)
//    public void exportExcel(@PathVariable(name="mssv") String mssv,
//                            @PathVariable(name="programFullCode") String programFullCode,
//                            @RequestParam(value = "status",required = false) String status,
//                            @RequestParam(value = "roleType",required = false) RoleType roleType,
//                            HttpServletResponse httpServletResponse) throws IOException {
//        List<SubjectInfoDto> dtos = null;
//        if ("finished".equals(status)) {
//            dtos= studentInfoService.getFinishedSubject(mssv, programFullCode, roleType);
//        } else if ("unfinished".equals(status)) {
//            dtos= studentInfoService.getUnfinishedSubject(mssv, programFullCode,roleType);
//        } else {
//            dtos = studentInfoService.getAllSubject(mssv, programFullCode);
//        }
//
//        ExcelExporter exporter = new ExcelExporter();
//        List<String> headers = new ArrayList<>();
//        headers.add("STT");
//        headers.add("SubjectName");
//        headers.add("Credit");
//        headers.add("RoleType");
//        headers.add("Mark");
//        List<List<String>> listObject = new ArrayList<>();
//        int stt=1;
//        for (SubjectInfoDto s : dtos) {
//            List<String> gt = new ArrayList<>();
//            stt+=1;
//            gt.add(String.valueOf(stt));
//            if (s.getSubjectName()!=null) {
//                gt.add(s.getSubjectName());
//            } else {
//                gt.add("");
//            }
//            if (s.getCredit()!=null) {
//                gt.add(String.valueOf(s.getCredit()));
//            } else {
//                gt.add("");
//            }
//            if (s.getRoleType()!=null) {
//                gt.add(String.valueOf(s.getRoleType()));
//            } else {
//                gt.add("");
//            }
//            if (s.getMark()!=null) {
//                gt.add(String.valueOf(s.getMark()));
//            } else {
//                gt.add("");
//            }
//            listObject.add(gt);
//        }
//        exporter.export("search",headers,listObject, httpServletResponse);
//    }

//    @GetMapping("/runvalue")
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.OK)
//    public void value(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
//        System.out.println("sadfdsfadfdfadfasf");
//        ExcelExporter exporter = new ExcelExporter();
//        List<String> headers = new ArrayList<>();
//        headers.add("STT");
//        headers.add("SubjectName");
//        headers.add("Credit");
//        headers.add("RoleType");
//        headers.add("Mark");
//        List<List<String>> listObject = new ArrayList<>();
//        int stt=1;
//        List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubject("22028245", "CN8-2019", null);
//        for (SubjectInfoDto s : dtos) {
//            List<String> gt = new ArrayList<>();
//            stt+=1;
//            gt.add(String.valueOf(stt));
//            if (s.getSubjectName()!=null) {
//                gt.add(s.getSubjectName());
//            } else {
//                gt.add("");
//            }
//            if (s.getCredit()!=null) {
//                gt.add(String.valueOf(s.getCredit()));
//            } else {
//                gt.add("");
//            }
//            if (s.getRoleType()!=null) {
//                gt.add(String.valueOf(s.getRoleType()));
//            } else {
//                gt.add("");
//            }
//            if (s.getMark()!=null) {
//                gt.add(String.valueOf(s.getMark()));
//            } else {
//                gt.add("");
//            }
//            listObject.add(gt);
//        }
//        exporter.export("search",headers,listObject, httpServletResponse);
//    }


    @GetMapping("/getAverageMark/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public AverageMark getAverageMarkByRoleType(@PathVariable(name="mssv") String mssv,
                                                @PathVariable(name="programFullCode") String programFullCode,
                                                @RequestParam(value = "roleType",required = false) RoleType roleType) {
            AverageMark averageMark = new AverageMark();
            List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubject(mssv, programFullCode, roleType);
            Program program = studentInfoService.getProgram(programFullCode);
            averageMark.setAverageMark(studentInfoService.getMaxAverageMark(dtos,program,roleType));
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



    @GetMapping("/graduation/{mssv}/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public GraduatedCondition enoughCert(@PathVariable(name="mssv") String mssv,
                                         @PathVariable(name="programFullCode") String programFullCode) {
        GraduatedCondition graduatedCondition = new GraduatedCondition();
        Period period = studentInfoService.dateDiff(mssv,programFullCode);
        String suffixYear = period.getYears() > 1 ? " years," : " year,";
        String suffixMonth = period.getMonths() > 1 ? " months," : " month,";
        String suffixDay = period.getDays() > 1 ? " days." : " day.";
        Program program = studentInfoService.getProgram(programFullCode);
        boolean enoughCert = studentInfoService.enoughCertificate(mssv, program);
        String periodTimeStudied = "You have studied for "
                + period.getYears() + suffixYear
                + period.getMonths() + suffixMonth
                + period.getDays() + suffixDay;
        System.out.println("SUCESS");
        List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubject(mssv, programFullCode, null);
        EnoughCredit totalCredit = studentInfoService
                .numberStudiedCredit(dtos,program,null);
        EnoughCredit totalMandatory = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.MANDATORY);
        EnoughCredit totalOptional = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.OPTIONAL);
        EnoughCredit totalOptionalReinforcement = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.OPTIONALREINFORCEMENT);
        EnoughCredit totalPhysical = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.PHYSICAL);
        EnoughCredit totalNationalDefense = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.NATIONALDEFENCE);
        EnoughCredit totalAddition = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.ADDITIONAL);
        EnoughCredit totalGraduationInternship = studentInfoService
                .numberStudiedCredit(dtos,program,RoleType.GRADUATIONINTERSHIP);
        boolean okGPA =true;
        Double averageMark =studentInfoService.getMaxAverageMark(dtos,program,null);
        graduatedCondition.setEnoughCert(enoughCert);
        graduatedCondition.setConditionDuration(periodTimeStudied);
        graduatedCondition.setNumberCredit(totalCredit.getContent());
        graduatedCondition.setCompletedMandatory(totalMandatory.getContent());
        graduatedCondition.setCompletedOptional(totalOptional.getContent());
        graduatedCondition.setCompletedOptionalReinforcement(totalOptionalReinforcement.getContent());
        graduatedCondition.setCompletedPhysical(totalPhysical.getContent());
        graduatedCondition.setCompletedNationalDefense(totalNationalDefense.getContent());
        graduatedCondition.setCompletedAdditional(totalAddition.getContent());
        graduatedCondition.setCompletedGraduationInternship(totalGraduationInternship.getContent());
        if (Double.compare(averageMark,2.0) <0) {
            okGPA = false;
            graduatedCondition.setGpaCondition("GPA: " + averageMark + " - "  +
                    "Không đủ điểm nhận bằng tốt nghiệp vì GPA < 2.0");
        } else if (Double.compare(averageMark,2.5) < 0 &&
                ( program.getProgramType() == ProgramType.InternationalProgram
                        || program.getProgramType() ==ProgramType.HonorsProgram) ) {
            graduatedCondition.setGpaCondition("GPA: " + averageMark +" - " +
                    "Chỉ nhận được bằng tốt nghiệp hệ đào tạo chuẩn vì GPA < 2.5");
        } else {
            graduatedCondition.setGpaCondition("GPA: " + averageMark +" - " +
                    "Đủ điều kiện");
        }
        if (enoughCert && totalCredit.isEnough()
                && totalMandatory.isEnough()
                && totalOptional.isEnough()
                && totalOptionalReinforcement.isEnough()
                && totalPhysical.isEnough()
                && totalNationalDefense.isEnough()
                && totalAddition.isEnough()
                && totalGraduationInternship.isEnough()
                && okGPA) {
            graduatedCondition.setGraduation(true);
        } else {
            graduatedCondition.setGraduation(false);
        }
        System.out.println("mâfasfasfasdfafasfasf");
        return graduatedCondition;
    }


}
