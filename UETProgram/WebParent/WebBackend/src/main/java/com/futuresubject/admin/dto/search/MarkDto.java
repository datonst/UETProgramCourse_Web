package com.futuresubject.admin.dto.search;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;


@Getter
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class MarkDto implements Serializable {
    Double mark;
    Integer credit;
    public MarkDto(Double mark, Integer credit) {
        this.mark = mark;
        this.credit = credit;
    }
}
