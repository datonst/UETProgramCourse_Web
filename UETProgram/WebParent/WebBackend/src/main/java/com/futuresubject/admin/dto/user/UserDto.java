package com.futuresubject.admin.dto.user;

import com.futuresubject.common.entity.Account.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String role;
    private String token;
}
