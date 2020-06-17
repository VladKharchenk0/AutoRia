package com.gmail.kharchenko55.vlad.controller.searchcontroller;

import com.google.gson.Gson;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping({"/index/search"})
public class SearchController {

    @Autowired
    private SearchHelper search;

    private OkHttpClient client = new OkHttpClient();

    @Value("${base.search.url}")
    private String searchUrl;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("search", new SearchHelper());
        return "search/search";
    }

//    @RequestMapping(value = "/s")
//    public  String getCarModel(@RequestBody Json json) throws IOException {
//        System.out.println(json);
//
//        int carBody =json.getCarBody();
//
//
//        System.out.println(carBody);
//        return "lsmfd";
//    }

    @PostMapping
    @ResponseBody
    public String postSearch(@RequestBody Json data) throws IOException {

        int carBody = data.getCarBody();
        int carBrand = data.getCarBrand();
        int carModel = data.getCarModel();
        // search.saveParameters(carBody, carBrand, carModel);

        HttpUrl.Builder builder = HttpUrl.parse(searchUrl).newBuilder();
        builder.addQueryParameter("body_id", Long.toString(carBody));
        builder.addQueryParameter("marka_id", Long.toString(carBrand));
        builder.addQueryParameter("model_id", Long.toString(carModel));
        builder.addQueryParameter("countpage", String.valueOf(100));

        Request request = new Request.Builder()
                .url(builder.build())
                .build();

        System.out.println(request);

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        System.out.println(json);

        List<Integer> ids = search.getIds(json);

        String response1 = new Gson().toJson(search.getCars(ids));
        System.out.println("fd;s..................................  "+response1);

        return response1;
    }
}