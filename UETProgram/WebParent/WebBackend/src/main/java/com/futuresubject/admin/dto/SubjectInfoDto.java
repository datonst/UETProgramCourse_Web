package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.RoleType;
import com.futuresubject.common.entity.Subject;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DTO for {@link Subject}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class SubjectInfoDto implements Serializable {
    String subjectName;
    Integer credit;
    RoleType roleType;
    Double mark;
}