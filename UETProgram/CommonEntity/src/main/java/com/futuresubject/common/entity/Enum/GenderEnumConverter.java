package com.futuresubject.common.entity.Enum;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenderEnumConverter implements Converter<String, GenderType> {

    @Override
    public GenderType convert(String source) {
        return GenderType.valueOf(source);
    }
}
