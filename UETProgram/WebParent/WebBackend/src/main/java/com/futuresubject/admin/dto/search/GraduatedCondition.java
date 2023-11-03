package com.futuresubject.admin.dto.search;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class GraduatedCondition  implements Serializable {
    boolean enoughCert;
    String conditionDuration;
    String numberCredit;
    String completedMandatory;
    String completedOptional;
    String completedOptionalReinforcement;
    String completedPhysical;
    String completedNationalDefense;
    String completedAdditional;
    String completedGraduationInternship;
    String gpaCondition;
    boolean graduation;
}
