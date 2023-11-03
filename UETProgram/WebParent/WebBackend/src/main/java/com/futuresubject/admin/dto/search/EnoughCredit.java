package com.futuresubject.admin.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class EnoughCredit {
    String content;
    boolean enough;

    public EnoughCredit() {
        content = "";
        enough = false;
    }
}
