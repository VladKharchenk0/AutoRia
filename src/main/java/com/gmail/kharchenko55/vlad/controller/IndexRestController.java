package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.common.Util;
import com.gmail.kharchenko55.vlad.model.car.Search;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController("/search/{carBody}/{carBrand}")
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

    @RequestMapping(method=RequestMethod.POST)
    public String save(@RequestBody Search search) {
        System.out.println(search.getCarBody() +" " + search.getCarBrand());
        return "future_response";
    }
}
