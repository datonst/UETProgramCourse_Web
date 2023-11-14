package com.futuresubject.admin.dto.search;

import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Subject;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * DTO for {@link Subject}
 */
@Getter
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class SubjectInfoDto implements Serializable {
    String subjectName;
    Integer credit;
    RoleType roleType;
    Double mark;
    public SubjectInfoDto(String subjectName, Integer credit, RoleType roleType, Double mark) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.roleType = roleType;
        this.mark = mark;
    }

    public SubjectInfoDto(String subjectName, Integer credit, RoleType roleType) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.roleType = roleType;
    }
}