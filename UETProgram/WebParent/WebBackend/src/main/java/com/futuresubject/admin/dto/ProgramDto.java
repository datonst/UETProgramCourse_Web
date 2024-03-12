package com.futuresubject.admin.dto;


import com.futuresubject.common.entity.Entity.Program;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Enum.ProgramType;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Program}
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
    Double duration;
    ProgramType programType;
    String facultyName;
    LevelLanguage levelLanguage;
    Integer totalCredits;
    Integer totalOfMandatory;
    Integer totalOfOptional;
    Integer totalOfOptionalReinforcement;
    Integer totalOfPhysical;
    Integer totalOfNationalDefense;
    Integer totalOfAdditional;
    Integer totalOfGraduationInternship;
    List<ProgramType> programTypeList;
    List<String> listOfFacultyName;
    List<LevelLanguage> levelLanguageList;
}