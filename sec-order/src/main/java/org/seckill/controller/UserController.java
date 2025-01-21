package org.seckill.controller;

import jakarta.annotation.Resource;
import org.seckill.entity.User;
import org.seckill.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(path = "/getAll")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/getById")
    public Optional<User> getUserById(@RequestParam(name = "id") long id) {
        return userService.findById(id);
    }

    @PostMapping(path = "/createUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
