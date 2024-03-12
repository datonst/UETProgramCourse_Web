package com.futuresubject.admin.service;

import com.futuresubject.admin.dto.ObtainCertDto;
import com.futuresubject.admin.mapper.ObtainCertMapper;
import com.futuresubject.admin.repository.ObtainCertRepository;
import com.futuresubject.admin.repository.ProgramRepository;
import com.futuresubject.admin.repository.StudentRepository;

import com.futuresubject.common.entity.Enum.CertificateType;
import com.futuresubject.common.entity.JoinTable.ObtainCert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObtainCertService {
    @Autowired
    private ObtainCertRepository obtainCertRepository;


    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private StudentRepository studentRepository;


    public ObtainCertDto getByStudentIdAndLevelLanguage(String studentId, CertificateType certificateType) {
        ObtainCert obtainCert = obtainCertRepository.findByStudentIdAndLevelLanguage(studentId,certificateType);
        return ObtainCertMapper.INSTANCE.toDto(obtainCert);
    }
    public List<ObtainCertDto> findAllObtainCert(Pageable pagination) {
        List<ObtainCertDto> obtainCertDtos = new ArrayList<>();
        Page<ObtainCert> obtainCerts = obtainCertRepository.findAll(pagination);
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


    public void deleteByStudentIdAndLevel(String studentId, CertificateType certificateType) {
        Integer id = obtainCertRepository.findId(studentId,certificateType);
        if (id!=null && id!=0) {
            obtainCertRepository.deleteById(id);
        }
//        obtainCertRepository.deleteByStudentIdAndLevel(studentId,levelLanguage);
    }



    public ObtainCert insert(ObtainCertDto obtainCertDto) {
        ObtainCert obtainCert = ObtainCertMapper.INSTANCE.toEntity(obtainCertDto);
        obtainCert.setStudent(studentRepository.findById(obtainCertDto.getStudentId()).get());
        Integer id = obtainCertRepository.findId(obtainCertDto.getStudentId(),obtainCertDto.getCertificateType());
        if (id!=null) {
            obtainCert.setId(id);
        }
        return obtainCertRepository.save(obtainCert);
    }

    public void updateFromDto(ObtainCertDto obtainCertDto) {
        ObtainCert obtainCert = ObtainCertMapper.INSTANCE.toEntity(obtainCertDto);
        obtainCert.setStudent(studentRepository.findById(obtainCertDto.getStudentId()).get());
        Integer id = obtainCertRepository.findId(obtainCertDto.getStudentId(),obtainCertDto.getCertificateType());
        if (id!=null) {
            obtainCert.setId(id);
        }
        obtainCertRepository.save(obtainCert);
    }
}
