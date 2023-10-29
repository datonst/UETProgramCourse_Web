package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.ProgramDto;
import com.futuresubject.common.entity.Program;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramMapper {
    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
    Program toEntity(ProgramDto programDto);

    @Mapping(target = "facultyName",source="faculty.facultyName")
    ProgramDto toDto(Program program);

    List<ProgramDto> toDtoList(List<Program> programList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Program partialUpdate(ProgramDto programDto, @MappingTarget Program program);
}