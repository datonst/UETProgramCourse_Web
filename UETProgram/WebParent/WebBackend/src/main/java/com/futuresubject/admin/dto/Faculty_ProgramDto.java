package com.futuresubject.admin.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.Faculty_Program}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class Faculty_ProgramDto implements Serializable {
    String facultyName;
    String programFullCode;
    List<String> listOfFacultyName;
    List<String> listOfProgramFullCode;
}