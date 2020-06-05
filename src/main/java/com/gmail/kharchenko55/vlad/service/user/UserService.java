package com.gmail.kharchenko55.vlad.service.user;

import com.gmail.kharchenko55.vlad.controller.userscontroller.UserRegistrationDto;
import com.gmail.kharchenko55.vlad.model.user.User;

import java.util.List;

public interface UserService{
    List<User> getAllUsers();
    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
