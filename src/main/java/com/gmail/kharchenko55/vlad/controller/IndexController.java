package com.gmail.kharchenko55.vlad.controller;


import com.gmail.kharchenko55.vlad.common.Util;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IndexController {

    private OkHttpClient client = new OkHttpClient();
    private String baseUrl = Util.getBaseUrl();

    @GetMapping("/search")
    public String index() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl+ "category_id=1")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
