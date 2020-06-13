package com.gmail.kharchenko55.vlad.model.car;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.kharchenko55.vlad.common.Util;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data
class SearchParser {

    private String[] ids;
    private OkHttpClient client = new OkHttpClient();

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

        return car;
    }
}
