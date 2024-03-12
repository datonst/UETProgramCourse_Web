package com.futuresubject.common.entity.Enum;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CertificateTypeConverter implements Converter<String, CertificateType> {

    @Override
    public CertificateType convert(String source) {
        return CertificateType.valueOf(source);
    }
}
