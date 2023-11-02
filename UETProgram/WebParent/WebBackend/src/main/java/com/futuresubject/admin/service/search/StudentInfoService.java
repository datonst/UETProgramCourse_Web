package com.futuresubject.admin.service.search;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.admin.dto.search.SubjectInfoDto;
import com.futuresubject.admin.mapper.StudentInfoMapper;
import com.futuresubject.admin.mapper.SubjectInfoMapper;
import com.futuresubject.admin.repository.*;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Abstract.ConvertMark;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.util.*;
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


    public List<SubjectInfoDto> getAllSubject(String mssv, String programFullCode) {
        List<SubjectInfoDto> subjectInfoDtoList = new ArrayList<>();
        List<Subject> SubjectList = programSubjectRepository.findAllSubject(programFullCode);
        for (Subject subject : SubjectList) {
            SubjectInfoDto subjectInfoDto = SubjectInfoMapper.INSTANCE.toDto(subject);
            subjectInfoDtoList.add(subjectInfoDto);
        }
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
        List<SubjectInfoDto> subjectInfoDtoList = new ArrayList<>();
        if (roleType != null) {
            List<Subject> SubjectList = programSubjectRepository.findAllSubjectUnfinishedByRoleType(mssv, programFullCode,roleType);
            for (Subject subject : SubjectList) {
                SubjectInfoDto subjectInfoDto = SubjectInfoMapper.INSTANCE.toDto(subject);
                subjectInfoDtoList.add(subjectInfoDto);
            }
        } else {
            List<Subject> SubjectList = programSubjectRepository.findAllSubjectUnfinished(mssv, programFullCode);
            for (Subject subject : SubjectList) {
                SubjectInfoDto subjectInfoDto = SubjectInfoMapper.INSTANCE.toDto(subject);
                subjectInfoDtoList.add(subjectInfoDto);
            }
        }
        return subjectInfoDtoList;
    }

    public Double getMaxAverageMark(List<SubjectInfoDto> dtos, String programFullCode, RoleType roleType) {
        int number = Integer.MAX_VALUE;
        if (roleType != null) {
            Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
            number = RoleType.getTotalCredit(program,roleType);
        }
        List<SubjectInfoDto> values = dtos.stream().map(SerializationUtils::clone).collect(Collectors.toList());
        values.sort(Comparator.comparing(SubjectInfoDto::getMark).reversed());
        double sumMark = 0;
        int totalCredit = 0;
        for (SubjectInfoDto subjectInfoDto : values) {
            if (totalCredit >= number) {
                break;
            } else {
                sumMark += (ConvertMark.MarkToGPA(subjectInfoDto.getMark())
                        * subjectInfoDto.getCredit());
                totalCredit += subjectInfoDto.getCredit();
            }
        }
        return totalCredit == 0 ? totalCredit : sumMark / totalCredit;
    }


    public boolean downGraded(String mssv, String programFullCode) {
        List<SubjectInfoDto> dtos = getFinishedSubject(mssv, programFullCode, null);
        double countCredit = 0;
        for (SubjectInfoDto subjectInfoDto : dtos) {
            if (Double.compare(subjectInfoDto.getMark(),4.0d) <0 ){
                countCredit +=subjectInfoDto.getCredit();
            }
        }
        Program program = programRepository.findByProgramCodeAndAndPeriod(programFullCode);
        if (Double.compare(countCredit/program.getTotalCredits(),0.05d)>0) {
            return true;
        }
        return false;
    }

    public boolean enoughCertificate(String studentId,String programFullCode) {
        List<LevelLanguage> levelLanguageList =
                obtainCertRepository.findObtainCertByStudentId(studentId);

        LevelLanguage levelLanguage = programRepository.findLevelLanguage(programFullCode);
        String neededLevel =levelLanguage.toString();
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
        return compare;
    }
}
