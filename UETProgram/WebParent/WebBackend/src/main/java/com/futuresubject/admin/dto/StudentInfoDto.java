package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Entity.Program;
import com.futuresubject.common.entity.Entity.Student;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Student}
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
    String dateOfBirth;
    String gender;
    String address;
    String phone;
    String classFullName;
    /**
     * DTO Full Code for {@link Program}
     */
    List<String> ProgramFullCode; // name of program which is student study
    public void addProgramFullCode(String programFullCode) {
        if (this.ProgramFullCode==null) {
            this.ProgramFullCode = new ArrayList<>();
        }
        this.ProgramFullCode.add(programFullCode);
    }
}