package com.futuresubject.admin.mapper;

import com.futuresubject.admin.dto.user.SignUpDto;
import com.futuresubject.admin.dto.user.UserDto;
import com.futuresubject.common.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "enabled", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    User partialUpdate(UserDto userDto, @MappingTarget User user);
}
