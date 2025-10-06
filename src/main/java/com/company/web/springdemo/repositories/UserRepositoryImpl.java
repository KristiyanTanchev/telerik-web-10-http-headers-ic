package com.company.web.springdemo.repositories;

import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final List<User> users;


    public UserRepositoryImpl() {
        users = new ArrayList<>();
        users.add(new User(1, "Gosho", "123qwe!", "Georgi", "Georgiev",
                "gosho@abv.bg", true));
        users.add(new User(2, "Pesho", "peshkata@", "Petar", "Petrov",
                "pesho@abv.bg", false));
        users.add(new User(3, "Vankata95", "asd", "Ivan", "Ivanov",
                "vankata@abv.bg", false));
    }


    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User get(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", "id", String.valueOf(id)));
    }

    @Override
    public User get(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
    }
}
