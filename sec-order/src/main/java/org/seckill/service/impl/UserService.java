package org.seckill.service.impl;

import jakarta.annotation.Resource;
import org.seckill.entity.User;
import org.seckill.service.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public User createUser(User userInput) {
        User user = new User();
        user.setAccount(userInput.getAccount());
        user.setPassword(userInput.getPassword());
        user.setUsername(userInput.getUsername());
        user.setAvatar(userInput.getAvatar());
        user.setMobile(userInput.getMobile());
        user.setEmailAddr(userInput.getEmailAddr());
        return userRepository.save(user);
    }
}
