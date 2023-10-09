package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.common.entity.MarkSubject;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarkSubjectMapper {
    MarkSubjectMapper INSTANCE = Mappers.getMapper(MarkSubjectMapper.class);
    MarkSubject toEntity(MarkSubjectDto markSubjectDto);

    MarkSubjectDto toDto(MarkSubject markSubject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MarkSubject partialUpdate(MarkSubjectDto markSubjectDto, @MappingTarget MarkSubject markSubject);
}