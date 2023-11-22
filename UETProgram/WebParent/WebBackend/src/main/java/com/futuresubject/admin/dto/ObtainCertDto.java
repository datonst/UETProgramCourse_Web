package com.futuresubject.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.futuresubject.common.entity.Enum.CertificateType;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.ObtainCert}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class ObtainCertDto implements Serializable {
    String studentId;
    CertificateType certificateType;
    LevelLanguage levelLanguage;
    String submissionDate;
    List<CertificateType> certificateTypeList;
    List<LevelLanguage> levelLanguageList;
}