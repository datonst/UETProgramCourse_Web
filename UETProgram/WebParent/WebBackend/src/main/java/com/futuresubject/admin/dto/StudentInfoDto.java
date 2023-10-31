package com.futuresubject.admin.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.Student}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class StudentInfoDto implements Serializable {
    String studentId;
    String name;
    Integer age;
    String gender;
    String address;
    String phone;
    String classFullName;
    /**
     * DTO Full Code for {@link com.futuresubject.common.entity.Program}
     */
    List<String> ProgramFullCode; // name of program which is student study
    public void addProgramFullCode(String programFullCode) {
        if (this.ProgramFullCode==null) {
            this.ProgramFullCode = new ArrayList<>();
        }
        this.ProgramFullCode.add(programFullCode);
    }
}