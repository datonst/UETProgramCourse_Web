package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Entity.Classroom;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * DTO for {@link Classroom}
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