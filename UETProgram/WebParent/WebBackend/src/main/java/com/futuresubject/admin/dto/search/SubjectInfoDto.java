package com.futuresubject.admin.dto.search;

import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Subject;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Subject}
 */
@Getter
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInfoDto implements Serializable {
    String subjectName;
    Integer credit;
    RoleType roleType;
    Double mark;
    List<String> prerequisiteSubjectId;
    public SubjectInfoDto(String subjectName, Integer credit, RoleType roleType, Double mark) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.roleType = roleType;
        this.mark = mark;
    }

    public void setPrerequisiteSubjectToArray(Set<Subject> prerequisiteSubject) {
        this.prerequisiteSubjectId = prerequisiteSubject.stream()
                .map(Subject::getSubjectid)
                .collect(Collectors.toList());
    }

    public SubjectInfoDto(String subjectName, Integer credit, RoleType roleType) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.roleType = roleType;

    }
    public SubjectInfoDto(String subjectName, Integer credit, RoleType roleType, Set<Subject> prerequisiteSubject) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.roleType = roleType;

    }

}