package com.futuresubject.common.entity.Enum;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LevelLangugeConverter implements Converter<String, LevelLanguage> {
    @Override
    public LevelLanguage convert(String source) {
        return LevelLanguage.valueOf(source);
    }
}
