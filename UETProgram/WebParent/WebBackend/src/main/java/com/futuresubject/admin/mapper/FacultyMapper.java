package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.common.entity.Entity.Faculty;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FacultyMapper {
    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);
    Faculty toEntity(FacultyDto facultyDto);

    FacultyDto toDto(Faculty faculty);

    List<FacultyDto> toDtoList(List<Faculty> faculty);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Faculty partialUpdate(FacultyDto facultyDto, @MappingTarget Faculty faculty);
}