package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.common.entity.Attendance;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    Attendance toEntity(AttendanceDto attendanceDto);
    @Mapping(target = "studentId",source="student.studentId")
    @Mapping(target = "programFullCode",expression="java(attendance.getProgram().getProgramFullCode())")
    AttendanceDto toDto(Attendance attendance);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Attendance partialUpdate(AttendanceDto attendanceDto, @MappingTarget Attendance attendance);
}