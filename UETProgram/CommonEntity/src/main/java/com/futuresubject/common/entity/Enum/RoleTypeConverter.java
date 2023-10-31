package com.futuresubject.common.entity.Enum;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleTypeConverter implements Converter<String,RoleType> {
    @Override
    public RoleType convert(String source) {

        return RoleType.valueOf(source);
    }
}
