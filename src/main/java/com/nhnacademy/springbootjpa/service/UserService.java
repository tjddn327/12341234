package com.nhnacademy.springbootjpa.service;

import com.nhnacademy.springbootjpa.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    User getById(String id);

    User create(String id, String password, int age);

    User modifyById(String id, String password, int age);
}
