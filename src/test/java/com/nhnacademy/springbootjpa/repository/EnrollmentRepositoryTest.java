package com.nhnacademy.springbootjpa.repository;

import com.nhnacademy.springbootjpa.entity.Enrollment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO #7: 아래 `@Disabled` 어노테이션을 삭제하고 테스트를 통과시키세요.
@Disabled("temporary")
@DataJpaTest
class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Sql("enrollment-test.sql")
    @Test
    void test() {
        List<Enrollment> enrollments1 = enrollmentRepository.findEnrollmentByStudentId_Name("nhn");
        assertThat(enrollments1).hasSize(3);

        List<Enrollment> enrollments2 = enrollmentRepository.findEnrollmentByCourseId_Name("jpa");
        assertThat(enrollments2).hasSize(2);
    }

}
