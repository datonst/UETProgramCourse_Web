package com.futuresubject.admin.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * DTO for {@link com.futuresubject.common.entity.Faculty}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class FacultyDto implements Serializable {
    String facultyName;
    String address;;
    String email;
    String phone;
    String website;
}