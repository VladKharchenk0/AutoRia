package com.gmail.kharchenko55.vlad.service.user;

import com.gmail.kharchenko55.vlad.controller.userscontroller.UserRegistrationDto;
import com.gmail.kharchenko55.vlad.dao.UserRepository;
import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.model.user.UserRole;
import com.gmail.kharchenko55.vlad.model.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User update(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user.getUserStatus().equals(UserStatus.ACTIVE)) {
            user.setUserStatus(UserStatus.NOT_ACTIVE);
        } else {
            user.setUserStatus(UserStatus.ACTIVE);
        }
        userRepository.save(user);

        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User register(UserRegistrationDto registration) {
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        user.setUserStatus(UserStatus.NOT_ACTIVE);

        return userRepository.save(user);
    }
}
