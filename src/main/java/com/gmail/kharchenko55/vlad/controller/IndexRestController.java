package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.common.Util;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
public class IndexRestController {

    private OkHttpClient client = new OkHttpClient();
    private String baseUrl = Util.getBaseUrl();

    @GetMapping()
    @ResponseBody
    public String index(@PathVariable String carBody, @PathVariable String carBrand) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "bodystyle=" + carBody+"&marka_id="+carBrand)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
