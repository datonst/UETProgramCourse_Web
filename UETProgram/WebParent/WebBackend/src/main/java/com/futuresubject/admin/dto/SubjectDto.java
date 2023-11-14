package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Subject;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
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
public class SubjectDto implements Serializable {
    String subjectid;
    String subjectName;
    Integer credit;
    List<String> prerequisiteSubjectId;
    List<String> listOfSubjectId;

    public void addPrerequisite(String id) {

        this.prerequisiteSubjectId.add(id);
    }

}