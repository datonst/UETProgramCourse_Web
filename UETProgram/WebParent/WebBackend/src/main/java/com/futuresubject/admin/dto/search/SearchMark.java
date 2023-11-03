package com.futuresubject.admin.dto.search;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchMark implements Serializable {
    String studentId;
    String programName;
    Double sumMark;

    public SearchMark(String studentId, String programName, Double sumMark) {
        this.studentId = studentId;
        this.programName = programName;
        this.sumMark = sumMark;
    }
}
