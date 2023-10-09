package com.futuresubject.common.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class User {
    Integer uid;
    String email;
    String role;
    String password;
    public User(Integer uid, String email, String role, String password) {
        this.uid = uid;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
