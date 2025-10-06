package com.company.web.springdemo.services;

import com.company.web.springdemo.models.User;
import com.company.web.springdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public User get(String username) {
        return userRepository.get(username);
    }
}
