package com.futuresubject.admin.service.search;

import com.futuresubject.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@Transactional
@CrossOrigin
public class StatisticSearchService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private MarkSubjectRepository markSubjectRepository;
    @Autowired
    private Program_SubjectRepository programSubjectRepository;
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ObtainCertRepository obtainCertRepository;
    public List<String> searchStudentIdList(String cohort, String programFullCode) {
        return studentRepository.countByCohortAndProgram(cohort, programFullCode);
    }
    public String getProgramFullCode(String programCode,String cohort) {

        int c = Integer.parseInt(cohort.substring(1)) + 1955;
        int period=0;

        if (programCode.equals("CN12")) {
            if (c<2019) {
                return "-1";
            }
            return "CN12-2022";
        }
        if ((programCode.equals("CN10") || programCode.equals("CN11")) && c<2019) {
            return "-1";
        }
        if (c<=2018) {
            if (programCode.equals("CN8_CLC")) {
                period = 2016;
            } else {
                period = 2015;
            }
        } else if (c<=2022) {
            period =2019;
        } else {
            period = 2019;
        }
        return programCode+ "-" +period;
    }


}
