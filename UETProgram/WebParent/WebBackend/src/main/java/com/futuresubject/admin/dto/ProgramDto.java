package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.ProgramType;
import jakarta.persistence.Column;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.Program}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class ProgramDto implements Serializable {
    String programCode;
    String programName;
    String period;
    ProgramType programType;
    String facultyName;
    Integer totalCredits;
    Integer totalOfMandatory;
    Integer totalOfOptional;
    Integer totalOfOptionalReinforcement;
    Integer totalOfPhysical;
    Integer totalOfNationalDefense;
    Integer totalOfAdditional;
    Integer totalOfGraduationInternship;
    List<ProgramType> programTypeList = Arrays.asList(ProgramType.values());
    List<String> listOfFacultyName;
}