package com.gmail.kharchenko55.vlad.controller.searchcontroller;

import com.gmail.kharchenko55.vlad.model.car.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/index/search" })
public class SearchController {
    @GetMapping
    public String main(Model model) {
        model.addAttribute("search", new Search());
        return "search/search";
    }
}
