package com.gmail.kharchenko55.vlad.controller.searchcontroller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.kharchenko55.vlad.common.Util;
import com.gmail.kharchenko55.vlad.model.car.Car;
import com.gmail.kharchenko55.vlad.model.search.SearchHistory;
import com.gmail.kharchenko55.vlad.service.search.SearchHistoryImpl;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SearchHistoryImpl searchHistory;

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
        String url = Util.getInfoUrl();

        List<Car> cars = new ArrayList<>();
        for (int id : ids) {
            HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
            builder.addQueryParameter("auto_id", String.valueOf(id));
            Request request = new Request.Builder()
                    .url(builder.build())
                    .build();

            Response response = client.newCall(request).execute();

            cars.add(getCarFromJson(response.body().string()));
        }

        return cars;
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

        return car;
    }

    public void saveParameters( int carBody, int carBrand, int carModel){
        SearchHistory search = new SearchHistory();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        search.setEmail(((UserDetails)principal).getUsername());
        search.setCarBody(carBody);
        search.setCarBrand(carBrand);
        search.setCarModel(carModel);

        searchHistory.save(search);

    }
}
