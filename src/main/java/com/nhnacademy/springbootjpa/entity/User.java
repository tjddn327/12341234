package com.nhnacademy.springbootjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {
    @Length(min = 3, max = 50)
    @Id
    private String id;

    @Length(min = 3, max = 20)
    @NotNull
    @Setter
    private String password;

    @NotNull
    @Setter
    private int age;
}
