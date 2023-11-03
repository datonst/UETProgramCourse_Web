package com.futuresubject.admin.restcontroller.search;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.search.*;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.admin.service.search.StudentInfoService;
import com.futuresubject.common.entity.Enum.ProgramType;
import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
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
        return graduatedCondition;
    }


}
