package com.gmail.kharchenko55.vlad.controller.userscontroller;


import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.service.search.SearchHistoryImpl;
import com.gmail.kharchenko55.vlad.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final SearchHistoryImpl historyService;

    @Autowired
    public UserController(UserService userService, SearchHistoryImpl historyService) {
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping("/show-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "admin/show_users";
    }

    @RequestMapping(value = "/activate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeStatus(@PathVariable(value = "id") String id, Model model) {
        User user = userService.update(Integer.parseInt(id));
        model.addAttribute(user);

        return "admin/status_changed";
    }

    @GetMapping("/show-queries")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listOfQueries(Model model) {
        model.addAttribute("history", historyService.getAllHistory());

        return "admin/show_queries";
    }

    @GetMapping("/user-history")
    public String userHistory(Principal principal, Model model) {
        model.addAttribute("history", historyService.getAllByEmail(principal.getName()));
        model.addAttribute("email", principal.getName());

        return "search/user_history";
    }
}
