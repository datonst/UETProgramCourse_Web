package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.common.entity.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    Student toEntity(StudentDto studentDto);

    @Mapping(target = "classFullName",expression="java(student.getClassFullName())")
    StudentDto toDto(Student student);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}