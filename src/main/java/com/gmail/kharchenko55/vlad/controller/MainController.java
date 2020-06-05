package com.gmail.kharchenko55.vlad.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String root(Principal principal, Model model) {
        model.addAttribute("email", principal.getName());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/log_out")
    public String logOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
