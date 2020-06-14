package com.gmail.kharchenko55.vlad.controller.searchcontroller;

import com.gmail.kharchenko55.vlad.common.Util;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping({"/index/search"})
public class SearchController {

    @Autowired
    private SearchHelper search;

    private OkHttpClient client = new OkHttpClient();

    @GetMapping
    public String main(Model model) {
        model.addAttribute("search", new SearchHelper());
        return "search/search";
    }

    @PostMapping
    public ModelAndView postSearch(@RequestParam(name = "carBody") int carBody,
                                   @RequestParam(name = "carBrand") int carBrand,
                                   @RequestParam(name = "carModel") int carModel) throws IOException {

        String url = Util.getSearchUrl();
        //search.saveParameters(carBody, carBrand, carModel);

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("body_id", Long.toString(carBody));
        builder.addQueryParameter("marka_id", Long.toString(carBrand));
        builder.addQueryParameter("model_id", Long.toString(carModel));
        builder.addQueryParameter("countpage", String.valueOf(100));

        Request request = new Request.Builder()
                .url(builder.build())
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        List<Integer> ids = search.getIds(json);
        ModelAndView result = new ModelAndView("search/search.html");
        result.addObject("cars", search.getCars(ids));

        return result;
    }
}
