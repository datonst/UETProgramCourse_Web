package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.JoinTable.Faculty_Program;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Faculty_Program}
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