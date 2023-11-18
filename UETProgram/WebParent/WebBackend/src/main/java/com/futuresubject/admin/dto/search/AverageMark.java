package com.futuresubject.admin.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AverageMark implements Serializable {
    Double averageMark;
    Integer totalCredit;
}
