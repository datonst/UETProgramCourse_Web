package com.futuresubject.admin.service.search;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.search.EnoughCredit;
import com.futuresubject.admin.dto.search.SubjectInfoDto;
import com.futuresubject.admin.mapper.StudentInfoMapper;
import com.futuresubject.admin.mapper.SubjectInfoMapper;
import com.futuresubject.admin.repository.*;
import com.futuresubject.common.entity.Abstract.ConvertMark;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentInfoService {

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


    public List<SubjectInfoDto> getAllSubject(String mssv, String programFullCode,RoleType roleType) {
        if (roleType == null) {
            List<SubjectInfoDto> subjectInfoDtoList = programSubjectRepository.findAllSubject(programFullCode);
            return subjectInfoDtoList;
        }
        List<SubjectInfoDto> subjectInfoDtoList = programSubjectRepository.findAllSubjectByRoleType(programFullCode,roleType);
        return subjectInfoDtoList;

    }

    public StudentInfoDto getStudent(String mssv) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findById(mssv).get();
            StudentInfoDto stDto = StudentInfoMapper.INSTANCE.toDto(student);
            List<Program> listP = attendanceRepository.listOfProgram(student);
            for (Program p : listP) {
                stDto.addProgramFullCode(p.getProgramCode() + '-' + p.getPeriod());
            }
            return stDto;
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Could not find any user with mssv " + mssv);
        }
    }

    public List<SubjectInfoDto> getFinishedSubject(String mssv, String programFullCode, RoleType roleType) {
        if (roleType == null) {
            return markSubjectRepository.getALlMarkByStudentAndProgram(mssv, programFullCode);

        } else {
            return markSubjectRepository.getALlMarkByRoleType(mssv, programFullCode, roleType);
        }
    }

    public List<SubjectInfoDto> getUnfinishedSubject(String mssv, String programFullCode, RoleType roleType) {
        List<SubjectInfoDto> subjectInfoDtoList = null;
        if (roleType != null) {
            subjectInfoDtoList = programSubjectRepository.findAllSubjectUnfinishedByRoleType(mssv, programFullCode, roleType);
        } else {
            subjectInfoDtoList = programSubjectRepository.findAllSubjectUnfinished(mssv, programFullCode);
        }
        return subjectInfoDtoList;
    }

    public Double getMaxAverageMark(List<SubjectInfoDto> dtos, Program program, RoleType roleType) {
        int numberMax = Integer.MAX_VALUE;
        if (roleType != null) {
            numberMax = RoleType.getTotalCredit(program, roleType);
        }
        List<SubjectInfoDto> values = dtos.stream().map(SerializationUtils::clone).collect(Collectors.toList());
        values.sort(Comparator.comparing(SubjectInfoDto::getMark).reversed());
        double sumMark = 0;
        int totalCredit = 0;
        for (SubjectInfoDto subjectInfoDto : values) {
            if (subjectInfoDto.getRoleType() == RoleType.NATIONALDEFENCE || subjectInfoDto.getRoleType() == RoleType.PHYSICAL) {
                continue;
            }
            if (totalCredit >= numberMax) {
                break;
            } else {
                sumMark += (ConvertMark.MarkToGPA(subjectInfoDto.getMark())
                        * subjectInfoDto.getCredit());
                totalCredit += subjectInfoDto.getCredit();
            }
        }
        Double result =sumMark / totalCredit;
        result = ((double) Math.round(result*100))/100;
        return totalCredit == 0 ? totalCredit :result ;
    }


    public boolean downGraded(String mssv, String programFullCode) {
        List<SubjectInfoDto> dtos = getFinishedSubject(mssv, programFullCode, null);
        double countCredit = 0;
        for (SubjectInfoDto subjectInfoDto : dtos) {
            if (Double.compare(subjectInfoDto.getMark(), 4.0d) < 0) {
                countCredit += subjectInfoDto.getCredit();
            }
        }
        Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
        return Double.compare(countCredit / program.getTotalCredits(), 0.05d) > 0;
    }

    public boolean enoughCertificate(String studentId, Program program) {
        List<LevelLanguage> levelLanguageList =
                obtainCertRepository.findObtainCertByStudentId(studentId);
        LevelLanguage levelLanguage = program.getLevelLanguage();
        if (levelLanguage==null) {
            return true;
        }
        String neededLevel = levelLanguage.toString();
        String[] neededOfStr = neededLevel.split("_", 2);
        boolean compare = false;
        for (LevelLanguage level : levelLanguageList) {
            String levelStr = level.toString();
            String[] levelOfStr = levelStr.split("_", 2);
            if (neededOfStr[0].equals(levelOfStr[0])) {
                int value1 = Integer.parseInt(neededOfStr[1]);
                int value2 = Integer.parseInt(levelOfStr[1]);
                if (value1 <= value2) {
                    compare = true;
                    break;
                }
            }
        }
        System.out.println("HELLO");
        return compare;
    }

    public Period dateDiff(String studentId, String programFullCode) {
        LocalDate attendanceDate = attendanceRepository.findAttendanceDate(studentId, programFullCode);
        ZoneId zoneBangkok = ZoneId.of("Asia/Bangkok");
        ZonedDateTime now = ZonedDateTime.now();
        LocalDate nowDate = now.toLocalDate();
        Period period = Period.between(attendanceDate, nowDate);
        return period;
    }


    public Program getProgram(String programFullCode) {
        Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
        return program;
    }
    public EnoughCredit numberStudiedCredit(List<SubjectInfoDto> dtos, Program program,RoleType roleType) {
        EnoughCredit enoughCredit = new EnoughCredit();
        int numberMax = Integer.MAX_VALUE;
        if (roleType != null) {
            numberMax = RoleType.getTotalCredit(program, roleType);
        } else {
            numberMax = program.getTotalCredits();
        }
        List<SubjectInfoDto> values = new ArrayList<>();
        for (SubjectInfoDto s : dtos) {
            if (roleType == null) {
                if (s.getRoleType() != RoleType.PHYSICAL && s.getRoleType()  != RoleType.NATIONALDEFENCE){
                    values.add(s);
                }
            }
            else if (  s.getRoleType() == roleType) {
                values.add(s);
            }
        }
        values.sort(Comparator.comparing(SubjectInfoDto::getMark).reversed());
        int totalCredit = 0;
        for (SubjectInfoDto subjectInfoDto : values) {
            if (totalCredit >= numberMax) {
                enoughCredit.setEnough(true);
                break;
            } else {
                totalCredit += subjectInfoDto.getCredit();
            }
        }
        enoughCredit.setContent((totalCredit)+ "/" + numberMax);
        return enoughCredit;
    }
}
