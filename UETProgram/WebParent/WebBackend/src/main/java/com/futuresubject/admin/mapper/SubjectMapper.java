package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.common.entity.Subject;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toEntity(SubjectDto subjectDto);


    @Mapping(target = "prerequisiteSubjectId",expression="java(subject.getPrerequisiteSubjectId())")
    SubjectDto toDto(Subject subject);
    List<SubjectDto> toDtoList(List<Subject> subjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subject partialUpdate(SubjectDto subjectDto, @MappingTarget Subject subject);
}