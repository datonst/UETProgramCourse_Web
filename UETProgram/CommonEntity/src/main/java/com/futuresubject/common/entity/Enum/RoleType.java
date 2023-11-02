package com.futuresubject.common.entity.Enum;

import com.futuresubject.common.entity.Program;
import lombok.Getter;

@Getter
public enum RoleType {
    MANDATORY, OPTIONAL,
    OPTIONALREINFORCEMENT,
    PHYSICAL,
    NATIONALDEFENCE,
    ADDITIONAL,
    GRADUATIONINTERSHIP;
    public static Integer getTotalCredit(Program program, RoleType roleType) {
        Integer number =null;
        switch (roleType) {
            case MANDATORY:
                number = program.getTotalOfMandatory();
                break;
            case OPTIONAL:
                number = program.getTotalOfOptional();
                break;
            case OPTIONALREINFORCEMENT:
                number = program.getTotalOfOptionalReinforcement();
                break;
            case PHYSICAL:
                number = program.getTotalOfPhysical();
                break;
            case NATIONALDEFENCE:
                number = program.getTotalOfNationalDefense();
                break;
            case ADDITIONAL:
                number = program.getTotalOfAdditional();
                break;
            case GRADUATIONINTERSHIP:
                number = program.getTotalOfGraduationInternship();
                break;
        }
        return number;
    }
}
