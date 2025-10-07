package com.company.web.springdemo.helpers;

import com.company.web.springdemo.exceptions.AuthorizationException;
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
    public static final String AUTHORIZATION_NAME = "Authorization";
    private final UserService userService;

    @Autowired
    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }

    public User tryGetUser(HttpHeaders headers){
        if (!headers.containsKey(AUTHORIZATION_NAME)){
            throw new AuthorizationException();
        }
        String authentication = headers.getFirst(AUTHORIZATION_NAME);
        if (authentication == null){
            throw new AuthorizationException();
        }
        if (authentication.split(",").length < 2){
            throw new AuthorizationException();
        }

        try {
            User current = userService.get(authentication.split(",")[0]);

            if (!current.getPassword().equals(authentication.split(",")[1])){
                throw new AuthorizationException();
            }
            return current;
        }catch (EntityNotFoundException e){
            throw new AuthorizationException();
        }
    }
}
