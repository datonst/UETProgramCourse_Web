package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.ObtainCertDto;
import com.futuresubject.admin.mapper.ObtainCertMapper;
import com.futuresubject.admin.repository.AttendanceRepository;
import com.futuresubject.admin.repository.ObtainCertRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.StudentRepository;

import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.ObtainCert;
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ObtainCertService {
    @Autowired
    private ObtainCertRepository obtainCertRepository;


    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private StudentRepository studentRepository;


    public ObtainCertDto getByStudentIdAndLevelLanguage(String studentId, LevelLanguage levelLanguage) {
        ObtainCert obtainCert = obtainCertRepository.findByStudentIdAndLevelLanguage(studentId,levelLanguage);
        return ObtainCertMapper.INSTANCE.toDto(obtainCert);
    }
    public List<ObtainCertDto> findAllObtainCert() {
        List<ObtainCertDto> obtainCertDtos = new ArrayList<>();
        List<ObtainCert> obtainCerts = obtainCertRepository.listObtainCert();
        for (ObtainCert o : obtainCerts) {
            obtainCertDtos.add(ObtainCertMapper.INSTANCE.toDto(o));
        }
        return obtainCertDtos;
    }

    public List<ObtainCertDto> getByStudentId(String studentId) {
        List<ObtainCert> obtainCerts= obtainCertRepository.findByStudentId(studentId);
        List<ObtainCertDto> obtainCertDtos=new ArrayList<>();
        for (ObtainCert o : obtainCerts) {
            ObtainCertDto obtainCertDto = ObtainCertMapper.INSTANCE.toDto(o);
            obtainCertDtos.add(obtainCertDto);
        }
        return obtainCertDtos;
    }


    public void deleteByStudentIdAndLevel(String studentId, LevelLanguage levelLanguage) {
        Integer id = obtainCertRepository.findId(studentId,levelLanguage);
        obtainCertRepository.deleteById(id);
//        obtainCertRepository.deleteByStudentIdAndLevel(studentId,levelLanguage);
    }



    public ObtainCert insert(ObtainCertDto obtainCertDto) {
        ObtainCert obtainCert = ObtainCertMapper.INSTANCE.toEntity(obtainCertDto);
        obtainCert.setStudent(studentRepository.findById(obtainCertDto.getStudentId()).get());
        Integer id = obtainCertRepository.findId(obtainCertDto.getStudentId(),obtainCertDto.getLevelLanguage());
        if (id!=null) {
            obtainCert.setId(id);
        }
        return obtainCertRepository.save(obtainCert);
    }

    public void updateFromDto(ObtainCertDto obtainCertDto) {
        ObtainCert obtainCert = ObtainCertMapper.INSTANCE.toEntity(obtainCertDto);
        obtainCert.setStudent(studentRepository.findById(obtainCertDto.getStudentId()).get());
        Integer id = obtainCertRepository.findId(obtainCertDto.getStudentId(),obtainCertDto.getLevelLanguage());
        if (id!=null) {
            obtainCert.setId(id);
        }
        obtainCertRepository.save(obtainCert);
    }
}
