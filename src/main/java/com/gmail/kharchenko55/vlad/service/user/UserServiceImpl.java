package com.gmail.kharchenko55.vlad.service.user;


import com.gmail.kharchenko55.vlad.controller.userscontroller.UserRegistrationDto;
import com.gmail.kharchenko55.vlad.dao.UserRepository;
import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);

    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setUserRole(UserRole.ROLE_NEWCOMER);
        return userRepository.save(user);
    }

}
