package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.Faculty_ProgramDto;
import com.futuresubject.common.entity.JoinTable.Faculty_Program;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface Faculty_ProgramMapper {
    Faculty_ProgramMapper INSTANCE = Mappers.getMapper(Faculty_ProgramMapper.class);
    Faculty_Program toEntity(Faculty_ProgramDto faculty_ProgramDto);

    Faculty_ProgramDto toDto(Faculty_Program faculty_Program);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Faculty_Program partialUpdate(Faculty_ProgramDto faculty_ProgramDto, @MappingTarget Faculty_Program faculty_Program);
}