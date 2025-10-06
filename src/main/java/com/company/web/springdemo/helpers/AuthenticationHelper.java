package com.company.web.springdemo.helpers;

import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class AuthenticationHelper {
    public static final String AUTHORIZATION_USER_NAME = "Username";
    public static final String AUTHORIZATION_PASSWORD = "Password";
    private final UserService userService;

    @Autowired
    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }

    public User tryGetUser(HttpHeaders headers){
        if (!headers.containsKey(AUTHORIZATION_USER_NAME)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "The requested resource requires authentication.");
        }

        try {
            User current = userService.get(headers.getFirst(AUTHORIZATION_USER_NAME));
            if (!current.getPassword().equals(headers.getFirst(AUTHORIZATION_PASSWORD))){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Wrong password or username.");
            }
            return current;
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid username.");
        }
    }
}
