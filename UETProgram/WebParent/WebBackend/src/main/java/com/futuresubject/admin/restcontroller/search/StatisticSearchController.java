package com.futuresubject.admin.restcontroller.search;

import com.futuresubject.admin.dto.search.EnoughCredit;
import com.futuresubject.admin.dto.search.SubjectInfoDto;
import com.futuresubject.admin.service.search.StatisticSearchService;
import com.futuresubject.admin.service.search.StudentInfoService;
import com.futuresubject.common.entity.Enum.ProgramType;
import com.futuresubject.common.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticSearchController {
    @Autowired
    StatisticSearchService statisticSearchService;

    @Autowired
    StudentInfoService studentInfoService;
    @GetMapping("/statistic/graduation")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Map<String,Integer>> statisticGraduation(@Param("cohort") String cohort) {
        System.out.println("safafasfa");
        List<String> studentIdList = statisticSearchService
                .searchStudentIdList(cohort,null);
        Map<String,Map<String,Integer>> result=  new LinkedHashMap<>();
        Map<String,Integer> totalStudentByProgramLists=  new LinkedHashMap<>();
        Map<String,Integer> graduatedStudentByProgramLists=  new LinkedHashMap<>();
        int total =0;
        for (int i=1;i<=16;i++) {
            String programCode = "CN"+i;
            System.out.println(programCode);
            String programFullCode = statisticSearchService.getProgramFullCode(programCode,cohort);
            if (programFullCode.equals("-1")) {
                continue;
            }
            List<String> searchStudentIdList = statisticSearchService.searchStudentIdList(cohort,programFullCode);
            totalStudentByProgramLists.put(programFullCode, searchStudentIdList.size());
            int dem=0;
            System.out.println("FINISHED");
            for (String mssv : searchStudentIdList) {
                Program program = studentInfoService.getProgram(programFullCode);
                boolean enoughCert = studentInfoService.enoughCertificate(mssv, program);
                List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubject(mssv, programFullCode, null);
                EnoughCredit totalCredit = studentInfoService
                        .numberStudiedCredit(dtos,program,null);
                boolean okGPA =true;
                Double averageMark =studentInfoService.getMaxAverageMark(dtos,program,null);
                if (Double.compare(averageMark,2.0) <0) {
                    okGPA = false;
                }
                if (enoughCert && totalCredit.isEnough() && okGPA) {
                    dem+=1;
                }
            }
            graduatedStudentByProgramLists.put(programFullCode,dem);
            total+=dem;
        }


        totalStudentByProgramLists.put("All",studentIdList.size());
        graduatedStudentByProgramLists.put("All",total);
        result.put("total",totalStudentByProgramLists);
        result.put("graduated",graduatedStudentByProgramLists);
        return result;
    }

}
