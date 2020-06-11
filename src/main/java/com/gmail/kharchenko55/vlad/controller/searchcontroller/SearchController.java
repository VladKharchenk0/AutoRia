package com.gmail.kharchenko55.vlad.controller.searchcontroller;

import com.gmail.kharchenko55.vlad.common.Util;
import com.gmail.kharchenko55.vlad.model.car.Search;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping({"/index/search"})
public class SearchController {

    private OkHttpClient client = new OkHttpClient();

    @GetMapping
    public String main(Model model) {
        model.addAttribute("search", new Search());
        return "search/search";
    }

    @PostMapping
    public ModelAndView postSearch(@RequestParam(name = "carBody") long carBody,
                                   @RequestParam(name = "carModel") long carModel) throws IOException {

        String url = Util.getBaseUrl();

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("carBody", Long.toString(carBody));
        builder.addQueryParameter("carModel", Long.toString(carModel));

        Request request = new Request.Builder()
                .url(builder.build())
                .build();

        Response response = client.newCall(request).execute();

        ModelAndView result = new ModelAndView("search/search.html");
        result.addObject("searchResult", response.body().string());

        return result;
    }
}
