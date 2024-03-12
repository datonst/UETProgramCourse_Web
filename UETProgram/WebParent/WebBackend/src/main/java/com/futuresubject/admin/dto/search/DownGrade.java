package com.futuresubject.admin.dto.search;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DownGrade implements Serializable {
    boolean downGrade;
}
