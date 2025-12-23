package com.example.springlesson.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.entity.User;
import com.example.springlesson.form.UserUpdateForm;
import com.example.springlesson.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("ユーザーが存在しません");
        }
        return user;
    }


    @Transactional
    public void updateUser(String email, UserUpdateForm form) {
        User user = findByEmail(email);

        user.setName(form.getName());
        user.setPhoneNumber(form.getPhoneNumber());
        user.setDmOptIn(form.isDmOptIn());

        userRepository.save(user);
    }

    /** 退会処理（論理削除） */
    @Transactional
    public void withdraw(String email) {
        User user = findByEmail(email);
        user.setWithdrawFlag(false);
        userRepository.save(user);
    }
}
