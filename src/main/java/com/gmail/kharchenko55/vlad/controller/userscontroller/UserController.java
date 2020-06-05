package com.gmail.kharchenko55.vlad.controller.userscontroller;

import com.gmail.kharchenko55.vlad.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show-users")
    public String listUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin/show_users";
    }
}
