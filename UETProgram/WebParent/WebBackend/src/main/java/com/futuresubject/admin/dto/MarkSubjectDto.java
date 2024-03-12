package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Entity.MarkSubject;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link MarkSubject}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class MarkSubjectDto implements Serializable {
    String studentId;
    String subjectId;
    Double mark;
    List<String> listOfStudentId;
    List<String> listOfSubjectId;
}