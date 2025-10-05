package com.nhnacademy.shoppingmall.model.user.service.impl;

import com.nhnacademy.shoppingmall.model.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        int count = userRepository.countByUserId(user.getUserId());
        if (count > 0) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        int count = userRepository.countByUserId(user.getUserId());
        if (count == 0) {
            throw new UserNotFoundException(user.getUserId());
        }
        userRepository.update(user);
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        int count = userRepository.countByUserId(userId);
        if (count == 0) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteByUserId(userId);
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        User user = userRepository.findByUserIdAndUserPassword(userId, userPassword)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.updateLatestLoginAtByUserId(userId, java.time.LocalDateTime.now());

        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

}
