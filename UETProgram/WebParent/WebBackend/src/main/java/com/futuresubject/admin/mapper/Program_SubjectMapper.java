package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.Program_SubjectDto;
import com.futuresubject.common.entity.JoinTable.Program_Subject;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface Program_SubjectMapper {
    Program_SubjectMapper INSTANCE = Mappers.getMapper(Program_SubjectMapper.class);
    Program_Subject toEntity(Program_SubjectDto program_SubjectDto);

    Program_SubjectDto toDto(Program_Subject program_Subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Program_Subject partialUpdate(Program_SubjectDto program_SubjectDto, @MappingTarget Program_Subject program_Subject);
}