package com.futuresubject.admin.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * DTO for {@link com.futuresubject.common.entity.Classroom}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class ClassroomDto implements Serializable {
    String cohort;
    String nameClass;
}