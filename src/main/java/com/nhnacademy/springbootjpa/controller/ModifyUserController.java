package com.nhnacademy.springbootjpa.controller;

import com.nhnacademy.springbootjpa.entity.User;
import com.nhnacademy.springbootjpa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ModifyUserController {

    private final UserService userService;

    @PutMapping("/users/{id}")
    public User modifyById(@PathVariable("id") String id, @Valid @RequestBody ModifyUserRequest request) {
        return userService.modifyById(id, request.password(), request.age());
    }

}
