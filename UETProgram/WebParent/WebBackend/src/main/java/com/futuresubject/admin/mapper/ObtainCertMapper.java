package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.ObtainCertDto;
import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.common.entity.ObtainCert;
import com.futuresubject.common.entity.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObtainCertMapper {
    ObtainCertMapper INSTANCE = Mappers.getMapper(ObtainCertMapper.class);

    ObtainCert toEntity(ObtainCertDto obtainCertDto);

    @Mapping(target = "studentId",source="student.studentId")
    ObtainCertDto toDto(ObtainCert obtainCert);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ObtainCert partialUpdate(ObtainCertDto obtainCertDto, @MappingTarget ObtainCert obtainCert);
}