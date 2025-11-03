package com.nhnacademy.springbootjpa.service.impl;

import com.nhnacademy.springbootjpa.entity.User;
import com.nhnacademy.springbootjpa.error.ResourceNotFoundException;
import com.nhnacademy.springbootjpa.repository.UserRepository;
import com.nhnacademy.springbootjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User(id = " + id + ") not found."));
    }

    @Transactional
    @Override
    public User create(String id, String password, int age) {
        if (userRepository.existsById(id)) {
            throw new IllegalStateException("User(id = " + id + ") already exists.");
        }
        return userRepository.save(new User(id, password, age));
    }

    @Transactional
    @Override
    public User modifyById(String id, String password, int age) {
        User user = getById(id);
        user.setPassword(password);
        user.setAge(age);
        return user;
    }
}
