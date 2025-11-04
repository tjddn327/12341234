package com.nhnacademy.shoppingmall.model.user.service.impl;

import com.nhnacademy.shoppingmall.model.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.repository.JpaUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service // [추가] Spring Bean으로 등록
public class UserServiceImpl implements UserService {

    private final JpaUserRepository jpaUserRepository;

    // [추가] @Autowired로 JpaRepository 주입
    @Autowired
    public UserServiceImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    @Transactional(readOnly = true) // [추가] 트랜잭션 (읽기 전용)
    public User getUser(String userId){
        return jpaUserRepository.findById(userId).orElse(null);
    }

    @Override
    @Transactional // [추가] 트랜잭션
    public void saveUser(User user) {
        if (jpaUserRepository.existsById(user.getUserId())) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        jpaUserRepository.save(user);
    }

    @Override
    @Transactional // [추가] 트랜잭션
    public void updateUser(User user) {
        if (!jpaUserRepository.existsById(user.getUserId())) {
            throw new UserNotFoundException(user.getUserId());
        }
        jpaUserRepository.save(user); // save가 update 역할도 겸함
    }

    @Override
    @Transactional // [추가] 트랜잭션
    public void deleteUser(String userId) {
        if (!jpaUserRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        jpaUserRepository.deleteById(userId);
    }

    @Override
    @Transactional // [추가] 트랜잭션 (로그인 시간 업데이트 때문에)
    public User doLogin(String userId, String userPassword) {
        User user = jpaUserRepository.findByUserIdAndUserPassword(userId, userPassword)
                .orElseThrow(() -> new UserNotFoundException(userId + ": 로그인 실패"));

        jpaUserRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        return user;
    }

    @Override
    @Transactional(readOnly = true) // [추가] 트랜잭션 (읽기 전용)
    public List<User> getAllUser() {
        return jpaUserRepository.findAll();
    }

    // [추가] 스레드용 포인트 업데이트 메서드
    @Override
    @Transactional
    public void updateUserPoints(String userId, int points) {
        User user = jpaUserRepository.findById(userId).orElse(null);
        if (Objects.nonNull(user)) {
            int newPoint = user.getUserPoint() + points;
            user.setUserPoint(newPoint);
            jpaUserRepository.save(user);
            log.info("Point updated for user: {}, new point: {}", userId, newPoint);
        } else {
            log.warn("User not found for point update: {}", userId);
        }
    }
}