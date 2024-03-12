package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.common.entity.Entity.Classroom;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClassroomMapper {
    ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);
    Classroom toEntity(ClassroomDto classroomDto);

    ClassroomDto toDto(Classroom classroom);

    List<ClassroomDto> toDtoList(List<Classroom> classroomList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Classroom partialUpdate(ClassroomDto classroomDto, @MappingTarget Classroom classroom);
}