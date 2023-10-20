package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.StudentInfoDto;
import com.futuresubject.common.entity.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentInfoMapper {
    StudentInfoMapper INSTANCE = Mappers.getMapper(StudentInfoMapper.class);
    StudentInfoDto toDto(Student student);
    List<StudentInfoDto> toDtoList(List<Student> studentList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentInfoDto studentInfoDto, @MappingTarget Student student);
}