package com.futuresubject.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter

public enum RoleType {
    MANDATORY, OPTIONAL, OPTIONALREINFORCEMENT, PHYSICAL,NATIONALDEFENCE, ADDITIONAL, GRADUATIONINTERSHIP;
}
