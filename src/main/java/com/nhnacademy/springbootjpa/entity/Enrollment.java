package com.nhnacademy.springbootjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// TODO #3: `enrollment` 테이블과 매핑될 `Enrollment` Entity 클래스
//           Entity 매핑과 연관관계 매핑을 하세요.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "enrollment")
public class Enrollment {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Student studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Course courseId;

    @NotNull
    private LocalDateTime enrolledat;

}
