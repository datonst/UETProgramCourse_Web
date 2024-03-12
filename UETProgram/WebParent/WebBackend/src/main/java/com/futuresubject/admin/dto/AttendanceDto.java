package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.JoinTable.Attendance;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Attendance}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class AttendanceDto implements Serializable {
    String studentId;
    String programFullCode;
    String startDate;
    String endDate;
    List<String> listOfStudentId;
    List<String> listOfProgramFullCode;
}