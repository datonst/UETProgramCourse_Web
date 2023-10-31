package com.futuresubject.common.entity.Enum;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProgramTypeConverter implements Converter<String, ProgramType> {
    @Override
    public ProgramType convert(String source) {
        return ProgramType.valueOf(source);
    }
}
