package com.futuresubject.common.entity.Account;

import com.futuresubject.common.entity.Enum.GenderType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass

public class Person {
    private String name;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String address;
    private String phone;

    public Person(String name, LocalDate dateOfBirth, GenderType gender, String address, String phone) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }
}
