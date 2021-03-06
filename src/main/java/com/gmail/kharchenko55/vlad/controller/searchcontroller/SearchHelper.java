package com.gmail.kharchenko55.vlad.controller.searchcontroller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.kharchenko55.vlad.model.car.Car;
import com.gmail.kharchenko55.vlad.model.search.SearchHistory;
import com.gmail.kharchenko55.vlad.service.search.SearchHistoryImpl;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data
class SearchHelper {

    private String[] ids;
    private OkHttpClient client = new OkHttpClient();
    private int submissionPeriod = 3;//last 3 day

    @Autowired
    private SearchHistoryImpl searchHistory;


    @Value("${base.info.url}")
    private String infoUrl;

    @Value("${base.search.url}")
    private String searchUrl;

    public List<Integer> getIds(String response) throws IOException {
        List<Integer> ids = new ArrayList<>();
        JsonNode searchNode = new ObjectMapper().readTree(response).get("result").get("search_result")
                .get("ids");
        for (final JsonNode objNode : searchNode) {
            ids.add(objNode.asInt());
        }

        return ids;
    }

    public List<Car> getCars(List<Integer> ids) throws IOException {
        List<Car> cars = new ArrayList<>();
        for (int id : ids) {
            HttpUrl.Builder builder = HttpUrl.parse(infoUrl).newBuilder();
            builder.addQueryParameter("auto_id", String.valueOf(id));
            Request request = new Request.Builder()
                    .url(builder.build())
                    .build();

            Response response = client.newCall(request).execute();

            cars.add(getCarFromJson(response.body().string()));
        }

        return cars;
    }

    public List<Car> getCarsByPeriod(int carBody, int carBrand, int carModel) throws IOException {
        HttpUrl.Builder builder = HttpUrl.parse(searchUrl).newBuilder();

        builder.addQueryParameter("body_id", Long.toString(carBody));
        builder.addQueryParameter("marka_id", Long.toString(carBrand));
        builder.addQueryParameter("model_id", Long.toString(carModel));
        builder.addQueryParameter("top", String.valueOf(submissionPeriod));
        builder.addQueryParameter("countpage", String.valueOf(100));

        Request request = new Request.Builder()
                .url(builder.build())
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        List<Integer> ids = getIds(json);

        return getCars(ids);
    }

    private Car getCarFromJson(String json) throws JsonProcessingException {
        Car car = new Car();
        JsonNode searchNode = new ObjectMapper().readTree(json);
        car.setMark(searchNode.get("markName").textValue());
        car.setModel(searchNode.get("modelName").textValue());
        car.setPrice(searchNode.get("USD").intValue());
        car.setCity(searchNode.get("stateData").get("name").textValue());
        car.setState(searchNode.get("stateData").get("regionName").textValue());
        car.setLinkToView(searchNode.get("linkToView").textValue());
        car.setPhotoUrl(searchNode.get("photoData").get("seoLinkF").textValue());

        return car;
    }

    public void saveParameters(int carBody, int carBrand, int carModel) {
        SearchHistory search = new SearchHistory();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        search.setEmail(((UserDetails) principal).getUsername());
        search.setCarBody(carBody);
        search.setCarBrand(carBrand);
        search.setCarModel(carModel);

        searchHistory.save(search);
    }
}
