package com.futuresubject.admin.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMark {
    String studentId;
    String programName;
    Double sumMark;

    public SearchMark(String studentId, String programName, Double sumMark) {
        this.studentId = studentId;
        this.programName = programName;
        this.sumMark = sumMark;
    }
}
