package com.nhnacademy.springbootjpa.repository;

import com.nhnacademy.springbootjpa.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Sql("user-test.sql")
    @Test
    void findUserTest() {
        // given
        String id = "admin";
        String password = "12345";
        int age = 15;

        // when
        User user = userRepository.findById(id).orElse(null);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getAge()).isEqualTo(age);
    }

    @Test
    void userNotFoundTest() {
        // given
        String id = "admi";

        // when
        User user = userRepository.findById(id).orElse(null);

        // then
        assertThat(user).isNull();
    }

    @Test
    void createUserTest() {
        // given
        String id = "test";
        String password = "12345";
        int age = 20;
        User user = new User(id, password, age);
        userRepository.save(user);

        // when
        User found = userRepository.findById(id).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(id);
        assertThat(found.getPassword()).isEqualTo(password);
        assertThat(found.getAge()).isEqualTo(age);
    }

}
