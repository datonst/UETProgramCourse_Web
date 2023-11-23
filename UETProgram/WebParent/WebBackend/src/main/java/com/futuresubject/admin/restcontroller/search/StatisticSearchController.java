package com.futuresubject.admin.restcontroller.search;

import com.futuresubject.admin.dto.search.AverageMark;
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

import java.util.*;

@RestController
public class StatisticSearchController {
    @Autowired
    StatisticSearchService statisticSearchService;

    @Autowired
    StudentInfoService studentInfoService;
    @GetMapping("/statistic/graduation")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String,String>> statisticGraduation(@Param("cohort") String cohort) {
        System.out.println("safafasfa");
        List<Map<String,String>> result=  new ArrayList<>();
        Map<String,String> programStatistic= null;
        int total_graduation =0;
        int total=0;
        for (int i=1;i<=16;i++) {
            String programCode = "CN"+i;
            System.out.println(programCode);
            String programFullCode = statisticSearchService.getProgramFullCode(programCode,cohort);
            if (programFullCode.equals("-1")) {
                System.out.println(programCode);
                continue;
            }
            System.out.println("-----------------------------------------------------------------");
            List<String> searchStudentIdList = statisticSearchService.searchStudentIdList(cohort,programFullCode);
            programStatistic=  new LinkedHashMap<>();
            int dem=0;
            for (String mssv : searchStudentIdList) {
                Program program = studentInfoService.getProgram(programFullCode);
                boolean enoughCert = studentInfoService.enoughCertificate(mssv, program);
                List<SubjectInfoDto> dtos = studentInfoService.getFinishedSubjectOrder(mssv, programFullCode, null);
                boolean okGPA =true;
                AverageMark averageMark =studentInfoService.getMaxAverageMark(dtos,program,null);
                if (Double.compare(averageMark.getAverageMark(),2.0) <0) {
                    okGPA = false;
                }
                if (enoughCert && program.getTotalCredits().compareTo(averageMark.getTotalCredit())<=0 && okGPA) {
                    dem+=1;
                }
            }
            programStatistic.put("name",programFullCode);
            programStatistic.put("grad",String.valueOf(dem));
            programStatistic.put("total", String.valueOf(searchStudentIdList.size()));
            result.add(programStatistic);
            total_graduation+=dem;
            total+=searchStudentIdList.size();

        }
        programStatistic =  new LinkedHashMap<>();
        programStatistic.put("name","All");
        programStatistic.put("grad",String.valueOf(total_graduation));
        programStatistic.put("total",String.valueOf(total));
        result.add(programStatistic);
        return result;
    }

}
