package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Enum.CertificateType;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.JoinTable.ObtainCert;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ObtainCert}
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